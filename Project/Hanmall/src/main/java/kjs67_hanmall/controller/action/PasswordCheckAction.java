package kjs67_hanmall.controller.action;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.LoginDTO;

public class PasswordCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		String user_pw = request.getParameter("user_pw");
		user_pw = encrypt(user_pw);
		
		HttpSession session = request.getSession();
		LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
		
		if (loginInfo.getUser_pw().equals(user_pw)) {
			forward.setRedirect(false);
			forward.setPath(Directory.EDITUSERINFO.getDirMid());
		} else {
			forward.setRedirect(false);
			forward.setPath(Directory.PASSWORDCHECK.getDirMid());
			session.setAttribute("alert", "잘못된 비밀번호입니다.");
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
