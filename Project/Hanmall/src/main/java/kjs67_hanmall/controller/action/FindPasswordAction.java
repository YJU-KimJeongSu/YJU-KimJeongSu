package kjs67_hanmall.controller.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.LoginDTO;

public class FindPasswordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		LoginDTO dto = new LoginDTO();
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		
		dto.setUser_id(user_id);
		dto.setUser_name(user_name);
		
		dto = dao.getUserPassword(dto);
		
		String prePw = dto.getUser_pw(); // 원래 비밀번호 (암호화 된 상태)
		if (prePw == null) {
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirFull());
			session.setAttribute("alert", "해당 회원 정보가 존재하지 않습니다.");
			return forward;
		}
		String newPw = prePw.substring(0, 5); // 5글자 잘라서 유저가 사용할 비암호화 비밀번호로
		String encryptedNewPw = encrypt(newPw); // newPw db에 등록하기 위해 암호화
		
		HanmallDAO dao2 = new HanmallDAO();
		dao2.changePassword(encryptedNewPw, dto); // newPw로 비밀번호 변경 (=newPw가 암호화 된걸로 db에 등록)
		dto.setUser_pw(newPw); // 변경된 비밀번호 보여주기 위해 셋
		
		request.setAttribute("pwResetResult", dto);
		
		// 이상한 이름/아이디 넣었을 떄
		if (dto.getUser_num() == 0) {
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirFull());
			session.setAttribute("alert", "해당 회원 정보가 존재하지 않습니다.");
		} else {
			// 테스트 계정 수정하려고 할 떄
			if (dto.getUser_num() <= 3) {
				forward.setRedirect(true);
				forward.setPath(Directory.INDEX.getDirFull());
				session.setAttribute("alert", "테스트 계정은 비밀번호를 재설정할 수 없습니다.");
				// 정상
			} else {
				forward.setRedirect(false);
				forward.setPath(Directory.FINDPWRESULT.getDirMid());
			}
		}
		
		return forward;
	}
	
	public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

}
