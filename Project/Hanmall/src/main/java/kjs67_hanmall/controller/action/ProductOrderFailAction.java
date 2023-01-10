package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.PaymentDTO;

public class ProductOrderFailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		PaymentDTO dto = new PaymentDTO();
		
		String user_phone = request.getParameter("user_phone1") + request.getParameter("user_phone2") + request.getParameter("user_phone3");
		String deliv_phone = request.getParameter("deliv_phone1") + request.getParameter("deliv_phone2") + request.getParameter("deliv_phone3");
		
		dto.setUser_num(Integer.parseInt(request.getParameter("user_num")));
		dto.setUser_name(request.getParameter("user_name"));
		dto.setUser_phone(user_phone);
		dto.setUser_zip(request.getParameter("user_zip"));
		dto.setUser_addr(request.getParameter("user_addr"));
		dto.setUser_detail_addr(request.getParameter("user_detail_addr"));
		
		dto.setDeliv_name(request.getParameter("deliv_name"));
		dto.setDeliv_phone(deliv_phone);
		dto.setDeliv_zip(request.getParameter("deliv_zip"));
		dto.setDeliv_addr(request.getParameter("deliv_addr"));
		dto.setDeliv_detail_addr(request.getParameter("deliv_detail_addr"));
		
		dto.setOrder_id(request.getParameter("orderId"));
		dto.setPay_method(request.getParameter("method"));
		dto.setPay_amount(Integer.parseInt(request.getParameter("amount")));
		dto.setPay_success(0);
		dto.setPay_date(request.getParameter("date"));
		
//		System.out.println("ProductOrderFailAction : dto : " + dto);
		
		boolean result = dao.paymentSuccess(dto);
		
		if (result) {
			forward.setRedirect(false);
			forward.setPath(Directory.INDEX.getDirMid());
			request.getSession().setAttribute("alert", "결제가 정상적으로 취소되었습니다.");
		} else {
			forward.setRedirect(true);
			forward.setPath(Directory.INDEX.getDirMid());
			request.getSession().setAttribute("alert", "에러가 발생했습니다. 다시 로그인해주세요");
			request.getSession().setAttribute("userInfo", null);
		}
		
		return forward;
	}

}
