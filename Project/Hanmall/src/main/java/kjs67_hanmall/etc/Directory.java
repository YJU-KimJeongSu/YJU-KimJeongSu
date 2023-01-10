package kjs67_hanmall.etc;

public enum Directory {
	INDEX("/Hanmall/index.jsp"),
	LOGIN("/Hanmall/com/yju/2wda/kjs67/view/Login.jsp"),
	CATEGORY("/Hanmall/com/yju/2wda/kjs67/view/Category.jsp"),
	FINDID("/Hanmall/com/yju/2wda/kjs67/view/Findid.jsp"),
	PAY("/Hanmall/com/yju/2wda/kjs67/view/Pay.jsp"),
	PRODUCTDETAIL("/Hanmall/com/yju/2wda/kjs67/view/ProductDetail.jsp"),
	PRODUCTMANAGE("/Hanmall/com/yju/2wda/kjs67/view/ProductManage.jsp"),
	PRODUCTREG("/Hanmall/com/yju/2wda/kjs67/view/ProductReg.jsp"),
	REGISTER("/Hanmall/com/yju/2wda/kjs67/view/Register.jsp"),
	CART("/Hanmall/com/yju/2wda/kjs67/view/Cart.jsp"),
	EDITUSERINFO("/Hanmall/com/yju/2wda/kjs67/view/EditUserInfo.jsp"),
	PASSWORDCHECK("/Hanmall/com/yju/2wda/kjs67/view/PasswordCheck.jsp"),
	ADMINPAGE("/Hanmall/com/yju/2wda/kjs67/view/AdminPage.jsp"),
	PRODUCTEDIT("/Hanmall/com/yju/2wda/kjs67/view/ProductEdit.jsp"),
	FINDIDRESULT("/Hanmall/com/yju/2wda/kjs67/view/FindidResult.jsp"),
	FINDPW("/Hanmall/com/yju/2wda/kjs67/view/FindPw.jsp"),
	FINDPWRESULT("/Hanmall/com/yju/2wda/kjs67/view/FindPwResult.jsp"),
	MYPAYHISTORY("/Hanmall/com/yju/2wda/kjs67/view/MyPayHistory.jsp"),
	PRODUCTIMAGE("/Hanmall/com/yju/2wda/kjs67/src/image/product/"),
	PRODUCTTHUMB("/Hanmall/com/yju/2wda/kjs67/src/image/product/thumb/");

	private String dir;
	
	Directory(String dir) {
		this.dir = dir;
	}
	
	public String getDirFull() {
		return this.dir;
	}
	
	public String getDirMid() {
		return this.dir.substring(8);
	}
}
