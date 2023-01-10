package kjs67_hanmall.controller.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;

public class DefaultErrorAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		session.setAttribute("userInfo", null);
		session.setAttribute("alert", "에러가 발생했습니다. 다시 로그인해주세요.");
		
		forward.setRedirect(true);
		forward.setPath(Directory.INDEX.getDirFull());
		return forward;
	}

}
