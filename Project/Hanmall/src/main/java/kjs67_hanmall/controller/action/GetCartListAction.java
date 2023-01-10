package kjs67_hanmall.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.LoginDTO;
import kjs67_hanmall.model.ProductDTO;

public class GetCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
//		System.out.println("GetCartList : its work!");
//		System.out.println("GetCartList : userInfo : " + request.getSession().getAttribute("userInfo"));
		HttpSession session = request.getSession();
		LoginDTO userInfo = (LoginDTO) session.getAttribute("userInfo");
		
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		result = dao.getCartList(userInfo);
		request.setAttribute("cartList", result);
		
		forward.setRedirect(false);
		forward.setPath(Directory.CART.getDirMid());
		return forward;
	}

}
