package kjs67_hanmall.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.LoginDTO;
import kjs67_hanmall.model.ProductDTO;

public class OrderPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("PaymentAction : its work");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();

		int prod_num = Integer.parseInt(request.getParameter("prod_num"));
		int buyCount = Integer.parseInt(request.getParameter("buyCount"));
		
		ProductDTO product = new ProductDTO();
		product.setProd_num(prod_num);
		product = dao.getProductInfo(product);
		ArrayList<ProductDTO> products = new ArrayList<ProductDTO>();
		products.add(product);
//		System.out.println("PaymentAction : product : " + product);
//		System.out.println("PaymentAction : buyCount : " + buyCount);
		
		request.setAttribute("productList", products);
		request.setAttribute("buyCount", buyCount);
		
		forward.setRedirect(false);
		forward.setPath(Directory.PAY.getDirMid());
		return forward;
	}

}
