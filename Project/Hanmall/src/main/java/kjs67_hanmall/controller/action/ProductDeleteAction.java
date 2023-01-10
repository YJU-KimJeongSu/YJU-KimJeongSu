package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;

public class ProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		
		int prod_num = Integer.parseInt(request.getParameter("prod_num"));
		
		boolean result = dao.deleteProduct(prod_num);
		
		if (result) {
			forward.setRedirect(false);
			forward.setPath("./productManage.han"); // 다시 상품 관리 페이지로 가도록
			request.getSession().setAttribute("alert", "정상적으로 삭제되었습니다.");
		} else {
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirMid());
			request.getSession().setAttribute("userInfo", null);
			request.getSession().setAttribute("alert", "에러가 발생했습니다. 다시 로그인해주세요");
		}
		
		return forward;
	}

}
