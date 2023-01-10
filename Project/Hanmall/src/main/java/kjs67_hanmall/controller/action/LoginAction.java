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

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
//		System.out.println("LoginAction : ok");
		HanmallDAO dao = new HanmallDAO();
		LoginDTO dto = new LoginDTO();
//		System.out.println("LoginAction : " + request.getParameter("user_id"));
//		System.out.println("LoginAction : " + request.getParameter("user_pw"));
		String user_pw = encrypt(request.getParameter("user_pw"));
		dto.setUser_id(request.getParameter("user_id"));
		dto.setUser_pw(user_pw);
		
		LoginDTO result = dao.login(dto);
//		request.setAttribute("userInfo", dto);
		HttpSession session = request.getSession();
		if (result != null) {
			session.setAttribute("userInfo", result);
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirFull());
		} else {
			session.setAttribute("alert", "잘못된 아이디 혹은 비밀번호입니다.");
			forward.setRedirect(true);
			forward.setPath(Directory.LOGIN.getDirFull());
		}
//		System.out.println("LoginAction : " + session.getAttribute("userInfo"));
		
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
