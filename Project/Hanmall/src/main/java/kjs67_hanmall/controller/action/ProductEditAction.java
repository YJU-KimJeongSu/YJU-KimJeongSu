package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.ProductDTO;

public class ProductEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("ProductEditAction : it's work!");
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		
		ProductDTO product = new ProductDTO();
		int prod_num = Integer.parseInt(request.getParameter("prod_num"));
		String prod_name = request.getParameter("productName");
		String prod_seller = request.getParameter("sellerName");
		int prod_price = Integer.parseInt(request.getParameter("price"));
		int prod_count = Integer.parseInt(request.getParameter("productCount"));
		String prod_summary = request.getParameter("productSummary");
		String prod_content = request.getParameter("editorTxt");
		
		product.setProd_num(prod_num);
		product.setProd_name(prod_name);
		product.setProd_seller(prod_seller);
		product.setProd_price(prod_price);
		product.setProd_count(prod_count);
		product.setProd_summary(prod_summary);
		product.setProd_content(prod_content);
//		System.out.println("ProductEditAction : product : " + product);
		boolean result = dao.updateProduct(product);
		
		if (result) {
			forward.setRedirect(false);
			forward.setPath("./productManage.han"); // 다시 상품 관리 페이지로 가도록
			request.getSession().setAttribute("alert", "정상적으로 수정되었습니다.");
		} else {
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirFull());
			request.getSession().setAttribute("userInfo", null);
			request.getSession().setAttribute("alert", "에러가 발생했습니다. 다시 로그인해주세요");
		}
		
		return forward;
	}

}
