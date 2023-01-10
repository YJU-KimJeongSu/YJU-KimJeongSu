package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", null);
		session.setAttribute("alert", "로그아웃 되었습니다.");
		
		forward.setRedirect(true);
		forward.setPath(Directory.INDEX.getDirFull());
		return forward;
	}

}
