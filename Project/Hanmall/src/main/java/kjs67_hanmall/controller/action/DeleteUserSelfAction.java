package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;

public class DeleteUserSelfAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		
		int user_num = Integer.parseInt(request.getParameter("user_num"));
		
		/* 테스트용 계정 수정 방지 */
		if (user_num == 1 || user_num == 2 || user_num == 3) {
			request.getSession().setAttribute("alert", "테스트 계정은 수정/삭제할 수 없습니다.");
			forward.setRedirect(false);
			forward.setPath(Directory.EDITUSERINFO.getDirMid()); // 바로 다시 회원정보 수정 창으로 보내지도록
			return forward;
		}
		
		new HanmallDAO().clearCart(user_num); // 그냥 유저 삭제하면 에러떠서 추가
		new HanmallDAO().clearpaymentList(user_num);
		boolean result = dao.deleteUser(user_num);
		
		if (result) {
			request.getSession().setAttribute("alert", "회원탈퇴가 성공적으로 되었습니다.");
		} else {
			request.getSession().setAttribute("alert", "문제가 발생했습니다. 다시 로그인해주세요");
		}
		
		request.getSession().setAttribute("userInfo", null);
		forward.setRedirect(true);
		forward.setPath(Directory.INDEX.getDirFull());
		return forward;
	}

}
