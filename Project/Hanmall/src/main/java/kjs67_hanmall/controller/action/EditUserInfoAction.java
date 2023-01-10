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

public class EditUserInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		LoginDTO dto = new LoginDTO();
		HttpSession session = request.getSession();
		
		int user_num = ((LoginDTO) session.getAttribute("userInfo")).getUser_num();
		
		/* 테스트용 계정 수정 방지 */
		if (user_num == 1 || user_num == 2 || user_num == 3) {
			request.getSession().setAttribute("alert", "테스트 계정은 수정/삭제할 수 없습니다.");
			forward.setRedirect(false);
			forward.setPath(Directory.EDITUSERINFO.getDirMid());
			return forward;
		}
		
		String user_pw = encrypt(request.getParameter("userPw"));
		
		dto.setUser_num(user_num);
		dto.setUser_id(request.getParameter("userID"));
		dto.setUser_pw(user_pw);
		dto.setUser_name(request.getParameter("userName"));
		String phone = request.getParameter("userPhone1");
		phone += request.getParameter("userPhone2");
		phone += request.getParameter("userPhone3");
		dto.setUser_phone(phone);
		dto.setUser_mail(request.getParameter("userMail"));
		dto.setUser_zip(request.getParameter("userZip"));
		dto.setUser_addr(request.getParameter("userAddr"));
		dto.setUser_detail_addr(request.getParameter("userDAddr"));
		
//		System.out.println("EditUserInfoAction : " + dto);
		
		boolean result = dao.updateUser(dto);
		
		if (result) {
			session.setAttribute("alert", "회원정보가 성공적으로 수정되었습니다. 다시 로그인해주세요");
		} else {
			session.setAttribute("alert", "회원정보 수정에 실패했습니다. 다시 로그인해주세요");
		}
		session.setAttribute("userInfo", null);
		forward.setRedirect(true);
		forward.setPath(Directory.INDEX.getDirFull());
		
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
