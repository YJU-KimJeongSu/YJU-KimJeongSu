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

public class RegisterAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		LoginDTO dto = new LoginDTO();
		HanmallDAO dao = new HanmallDAO();
		HttpSession session = request.getSession();
		String phone = request.getParameter("userPhone1") + request.getParameter("userPhone2") + request.getParameter("userPhone3");
		
		String user_pw = encrypt(request.getParameter("userPw"));
		
		
//		System.out.println("RegisterAction : " + request.getParameter("userID"));
		dto.setUser_id(request.getParameter("userID"));
//		dto.setUser_pw(request.getParameter("userPw"));
		dto.setUser_pw(user_pw);
		dto.setUser_name(request.getParameter("userName"));
		dto.setUser_phone(phone);
		dto.setUser_mail(request.getParameter("userMail"));
		dto.setUser_zip(request.getParameter("userZip"));
		dto.setUser_addr(request.getParameter("userAddr"));
		dto.setUser_detail_addr(request.getParameter("userDAddr"));
		
		boolean result = dao.register(dto);
		
		if (result) {
			session.setAttribute("alert", "회원가입이 완료되었습니다. 로그인해주세요");
			forward.setRedirect(true);
			forward.setPath(Directory.LOGIN.getDirFull());
		} else {
			session.setAttribute("alert", "회원가입에 실패했습니다. 다시 시도해주세요");
			forward.setRedirect(true);
			forward.setPath(Directory.REGISTER.getDirFull());
		}
		
		return forward;
	}
	
//	public String encrypt(String str) {
//		MessageDigest md = null;
//		try {
//			md = MessageDigest.getInstance("SHA-256");
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		byte[] str2 = str.getBytes();
//		md.update(str2);
//		byte[] encryptStr = md.digest();
//		String result = new String(encryptStr);
//		return result;
//	}
	
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
