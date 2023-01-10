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

public class CartListOrderAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO(); // 불러오기용 connection 객체
//		HanmallDAO dao2 = new HanmallDAO(); // 삭제용 connection 객체
		HttpSession session = request.getSession();
		LoginDTO userInfo = (LoginDTO) session.getAttribute("userInfo");
		
		int user_num = userInfo.getUser_num();
		ArrayList<ProductDTO> productList = dao.getProductListAtCart(user_num);
//		dao2.cartListOrder(user_num); // 주문 페이지로 갈때 삭제 -> 결제할 때 삭제로 변경
		
		request.setAttribute("productList", productList);
		
		forward.setRedirect(false);
		forward.setPath(Directory.PAY.getDirMid());
		
		return forward;
	}

}
