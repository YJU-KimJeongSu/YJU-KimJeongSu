package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.ProductDTO;

public class ProductEditPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		
		int prod_num = Integer.parseInt(request.getParameter("prod_num"));
//		System.out.println("ProductEditPageAction : prod_num : " + prod_num);
		ProductDTO product = new ProductDTO();
		product.setProd_num(prod_num);
		
		product = dao.getProductInfo(product);
//		System.out.println("ProductEditPageAction : product : " + product);
		
		request.setAttribute("product", product);
		
		forward.setRedirect(false);
		forward.setPath(Directory.PRODUCTEDIT.getDirMid());
		return forward;
	}

}
