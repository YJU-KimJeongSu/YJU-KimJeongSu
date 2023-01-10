package kjs67_hanmall.controller.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.Action;
import kjs67_hanmall.controller.ActionForward;
import kjs67_hanmall.etc.Directory;
import kjs67_hanmall.model.HanmallDAO;
import kjs67_hanmall.model.LoginDTO;

public class GetAllUserListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		HanmallDAO dao = new HanmallDAO();
		
		ArrayList<LoginDTO> list = new ArrayList<LoginDTO>();
		list = dao.getAllUserList();
		
		request.setAttribute("userList", list);
		
		forward.setRedirect(false);
		forward.setPath(Directory.ADMINPAGE.getDirMid());
		
		return forward;
	}

}
