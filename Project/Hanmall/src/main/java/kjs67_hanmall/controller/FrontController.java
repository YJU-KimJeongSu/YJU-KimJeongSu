package kjs67_hanmall.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kjs67_hanmall.controller.action.AddToCartAction;
import kjs67_hanmall.controller.action.CartListOrderAction;
import kjs67_hanmall.controller.action.CartRemoveAction;
import kjs67_hanmall.controller.action.DefaultErrorAction;
import kjs67_hanmall.controller.action.DeleteUserSelfAction;
import kjs67_hanmall.controller.action.DeleteUserWithAdminAction;
import kjs67_hanmall.controller.action.EditUserInfoAction;
import kjs67_hanmall.controller.action.FindIdAction;
import kjs67_hanmall.controller.action.FindPasswordAction;
import kjs67_hanmall.controller.action.GetAllUserListAction;
import kjs67_hanmall.controller.action.GetCartListAction;
import kjs67_hanmall.controller.action.GetDetailCategoryAction;
import kjs67_hanmall.controller.action.GetProductInfoAction;
import kjs67_hanmall.controller.action.GetUserPayHistoryAction;
import kjs67_hanmall.controller.action.IdCheck;
import kjs67_hanmall.controller.action.LoginAction;
import kjs67_hanmall.controller.action.LogoutAction;
import kjs67_hanmall.controller.action.OrderPageAction;
import kjs67_hanmall.controller.action.PasswordCheckAction;
import kjs67_hanmall.controller.action.ProductDeleteAction;
import kjs67_hanmall.controller.action.ProductEditAction;
import kjs67_hanmall.controller.action.ProductEditPageAction;
import kjs67_hanmall.controller.action.ProductManagementAction;
import kjs67_hanmall.controller.action.ProductOrderFailAction;
import kjs67_hanmall.controller.action.ProductOrderSuccessAction;
import kjs67_hanmall.controller.action.ProductRegAction;
import kjs67_hanmall.controller.action.ProductSearchAction;
import kjs67_hanmall.controller.action.RegisterAction;
import kjs67_hanmall.controller.action.SetUserClassAction;


public class FrontController extends HttpServlet implements Servlet {
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		command = command.substring(command.lastIndexOf("/"));
		
//		System.out.println(RequestURI);		// /Hanmall/login.han
//		System.out.println(contextPath);	// /Hanmall
//		System.out.println("FrontController : command : " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		switch (command) {
		case "/login.han" :
			action = new LoginAction();
			break;
			
		case "/logout.han" :
			action = new LogoutAction();
			break;
			
		case "/register.han" :
			action = new RegisterAction();
			break;
			
		case "/productReg.han" :
			action = new ProductRegAction();
			break;
			
		case "/category.han" :
			action = new GetDetailCategoryAction();
			break;
			
		case "/productDetail.han" :
			action = new GetProductInfoAction();
			break;
			
		case "/pay.han" :
			action = new OrderPageAction();
			break;
			
		case "/idCheck.han" :
			// action = new IdCheck();
			// 위 코드 쓰면 execute 실행되기 전 return되어서 코드 변경
			try {
				new IdCheck().execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return; // 페이지 이동을 없애기 위해 메소드 강제 종료
			
		case "/addToCart.han" :
			action = new AddToCartAction();
			break;
			
		case "/cartRemove.han" : 
			action = new CartRemoveAction();
			break;
			
		case "/cart.han" :
			action = new GetCartListAction();
			break;
			
		case "/cartListOrder.han" :
			action = new CartListOrderAction();
			break;
			
		case "/editUserInfo.han" :
			action = new EditUserInfoAction();
			break;
			
		case "/productSearch.han" :
			action = new ProductSearchAction();
			break;
			
		case "/userManage.han" : 
			action = new GetAllUserListAction();
			break;
			
		case "/changeUserClass.han" : 
			action = new SetUserClassAction();
			break;
			
		case "/deleteUserWithAdmin.han" :
			action = new DeleteUserWithAdminAction();
			break;
			
		case "/productManage.han" :
			action = new ProductManagementAction();
			break;
			
		case "/deleteUserSelf.han" :
			action = new DeleteUserSelfAction();
			break;
			
		case "/productEditPage.han" :
			action = new ProductEditPageAction();
			break;
			
		case "/productEdit.han" :
			action = new ProductEditAction();
			break;
			
		case "/productDelete.han" :
			action = new ProductDeleteAction();
			break;
			
		case "/findId.han" :
			action = new FindIdAction();
			break;
			
		case "/productOrderSuccess.han" :
			action = new ProductOrderSuccessAction();
			break;
			
		case "/productOrderFail.han" : 
			action = new ProductOrderFailAction();
			break;
			
		case "/pwCheck.han" :
			action = new PasswordCheckAction();
			break;
			
		case "/findPw.han" :
			action = new FindPasswordAction();
			break;
			
		case "/myPayHistory.han" :
			action = new GetUserPayHistoryAction();
			break;
			
		default:
			action = new DefaultErrorAction();
			break;
		}
		
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath()); // 새로운 요청
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response); // 요청 정보, URL 그대로 유지
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}
}
