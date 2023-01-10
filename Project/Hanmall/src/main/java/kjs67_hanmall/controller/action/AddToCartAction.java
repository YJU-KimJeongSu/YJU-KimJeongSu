package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.LoginDTO;

public class AddToCartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO(); // 존재여부 확인용 connection 객체
		HanmallDAO dao2 = new HanmallDAO(); // 카트에 담기용 connection 객체
		HttpSession session = request.getSession();
//		System.out.println("ActionForward : its work");
		int user_num = ((LoginDTO) session.getAttribute("userInfo")).getUser_num();
		int prod_num = Integer.parseInt(request.getParameter("prod_num"));
		int cart_count = Integer.parseInt(request.getParameter("buyCount"));
//		System.out.println("ActionForward : prod_num : " + user_num);
//		System.out.println("ActionForward : prod_num : " + prod_num);
//		System.out.println("ActionForward : buyCount : " + cart_count);
		
		boolean isExcist = dao.isAlreadyExist(user_num, prod_num); // false면 존재하지 않으므로 진행
		if (isExcist) { // true면 못담게 알려주고 메소드 종료
			session.setAttribute("alert", "이미 장바구니에 있는 상품입니다.");
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirFull());
			return forward;
		}
		
		boolean result = dao2.addToCart(user_num, prod_num, cart_count);
		
		if (result) {
			session.setAttribute("alert", "장바구니에 담겼습니다.");
			forward.setRedirect(false);
			forward.setPath(Directory.INDEX.getDirMid());
		} else {
			session.setAttribute("alert", "장바구니 담기에 실패했습니다.");
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirFull());
		}
		
		return forward;
	}

}
