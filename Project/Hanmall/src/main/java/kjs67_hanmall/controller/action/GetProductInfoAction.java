package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.ProductDTO;

public class GetProductInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		ProductDTO dto = new ProductDTO();
		
		int prod_num = Integer.parseInt(request.getParameter("prod_num"));
		dto.setProd_num(prod_num);
//		System.out.println("GetProductInfo : prod_num : " + prod_num);
		dto = dao.getProductInfo(dto);
		request.setAttribute("productInfo", dto);
		
		forward.setRedirect(false);
		forward.setPath(Directory.PRODUCTDETAIL.getDirMid());
		return forward;
	}

}
