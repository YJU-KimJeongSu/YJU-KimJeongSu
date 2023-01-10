package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.model.HanmallDAO;

public class IdCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("IdCheck : its work");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward(); // 페이지 이동 없으므로 설정 불필요
		HanmallDAO dao = new HanmallDAO();
		String user_id = request.getParameter("id");
//		System.out.println("IdCheck : user_id : " + user_id);
		
		int result = dao.checkID(user_id);
		response.getWriter().print(result);
		return forward;
	}

}
