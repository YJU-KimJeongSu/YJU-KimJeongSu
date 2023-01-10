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

public class ProductManagementAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		
		LoginDTO userInfo = (LoginDTO) request.getSession().getAttribute("userInfo");
		int user_num = userInfo.getUser_num();
		
		ArrayList<ProductDTO> userProductList = new ArrayList<ProductDTO>();
		userProductList = dao.getUserProductList(user_num);
		
//		for (ProductDTO p : userProductList) {
//			System.out.println("ProductManagementAction : userProduct : " + p);
//		}
		
		request.setAttribute("userProductList", userProductList);
		
		forward.setRedirect(false);
		forward.setPath(Directory.PRODUCTMANAGE.getDirMid());
		return forward;
	}

}
