package kjs67_hanmall.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.LoginDTO;

public class FindIdAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		LoginDTO dto = new LoginDTO();
		
//		System.out.println("FindIdAction : user_name : " + user_name);
//		System.out.println("FindIdAction : user_phone : " + user_phone);
		String user_name = request.getParameter("user_name");
		String user_phone = request.getParameter("userPhone1") + request.getParameter("userPhone2") + request.getParameter("userPhone3");
		
		dto.setUser_name(user_name);
		dto.setUser_phone(user_phone);
		
		dto = dao.findUserId(dto);
		
		String user_id = dto.getUser_id();
		
		if (user_id == null || user_id.equals("")) {
			forward.setRedirect(false);
			forward.setPath(Directory.FINDID.getDirMid());
			request.getSession().setAttribute("alert", "해당 유저 정보가 없습니다. 다시 확인해주세요");
		} else {
//			System.out.println("FindIdAction : user_id : " + user_id);
			forward.setRedirect(false);
			forward.setPath(Directory.FINDIDRESULT.getDirMid());
			request.setAttribute("user_id", user_id);
		}
		
		return forward;
	}

}
