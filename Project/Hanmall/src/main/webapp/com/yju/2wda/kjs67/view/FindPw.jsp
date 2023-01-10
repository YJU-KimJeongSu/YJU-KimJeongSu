<%@page import="kjs67_hanmall.model.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <link rel="stylesheet" href="../src/css/style.css">
    <link rel="stylesheet" href="../src/css/Findid.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
	<link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
<title>Insert title here</title>
</head>
<body>
	<%
	LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
	// if (loginInfo != null) System.out.println("index : now user class : " + loginInfo.getUser_class());
	
	int user_class = 0;
	if (loginInfo != null) {
		user_class = loginInfo.getUser_class();
	}
	%>
	
	<%
	if (user_class == 1) { // 일반 회원
		%>
		<%@ include file="./Header_login.jsp" %>
		<%
	} else if (user_class == 2) { // 판매자
		%>
		<%@ include file="./Header_seller.jsp" %>
		<%
	} else if (user_class == 9) { // 관리자
		%>
		<%@ include file="./Header_admin.jsp" %>
		<%
	} else { // 로그아웃
		%>
		<%@ include file="./Header_logout.jsp" %>
		<%
	}
	%>
	<form action="./findPw.han" method="post">
	    <div class="container">
	        <h2 class="font_Jua">비밀번호 재설정</h2>
	        <div class="form">
	            <label for="user_name" class="font_NanumSquareRoundEB">이름 <span class="red">* 　　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="user_name" name="user_name" required="required" maxlength="10">
	            <br>
	            <label for="userid" class="font_NanumSquareRoundEB">아이디 <span class="red">* 　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="userid" name="user_id" required="required">
	        </div>
	        <button type="submit" class="btn btn-secondary font_HannaPro searchbtn"><p class="font_HannaPro">비밀번호 재설정</p></button>
	    </div>
	</form>
    <%@ include file="./Footer.jsp" %>
</body>
</html>