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

public class CartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		HttpSession session = request.getSession();
		
		int cart_num = Integer.parseInt(request.getParameter("cart_num"));
		int removeResult = dao.removeCartItem(cart_num);
		
		if (removeResult == 1) {
			session.setAttribute("alert", "삭제되었습니다.");
		} else {
			session.setAttribute("alert", "삭제에 실패했습니다.");
		}
		
		
		/* GetCartListAction. 삭제 후 바로 장바구니로 보낼 수 있도록 */
		LoginDTO userInfo = (LoginDTO) session.getAttribute("userInfo");
		HanmallDAO dao2 = new HanmallDAO();
		
		ArrayList<ProductDTO> result = new ArrayList<ProductDTO>();
		result = dao2.getCartList(userInfo);
		request.setAttribute("cartList", result);
		
		forward.setRedirect(false);
		forward.setPath(Directory.CART.getDirMid());
		return forward;
	}

}
