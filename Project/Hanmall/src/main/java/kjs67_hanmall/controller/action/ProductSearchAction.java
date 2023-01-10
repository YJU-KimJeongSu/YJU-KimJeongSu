package kjs67_hanmall.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.ProductDTO;

public class ProductSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		String searchKeyword = request.getParameter("searchKeyword");
//		System.out.println("ProductSearchAction : searchKeyword : " + searchKeyword);
		
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		result = dao.getSearchResult(searchKeyword);
		
		request.setAttribute("products", result);
		forward.setRedirect(false);
		forward.setPath(Directory.CATEGORY.getDirMid());
		return forward;
	}

}
