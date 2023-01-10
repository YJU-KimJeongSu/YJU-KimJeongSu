<%@page import="kjs67_hanmall.model.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../src/css/style.css">
    <link rel="stylesheet" href="../src/css/Login.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
	<link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
    <title>Document</title>
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
    <div class="container">
        <div class="top font_Jua">
            <h2>로그인</h2>
        </div>
        <form method="post" action="./login.han">
	        <div class="mid">
	            <div class="mid-left">
	                <label for="userid" class="font_NanumSquareRoundEB">아이디　</label>
	                <input type="text" class="inputbox font_NanumSquareRoundB" id="userid" name="user_id">
	                <br>
	                <label for="userpassword" class="font_NanumSquareRoundEB">비밀번호</label>
	                <input type="password" class="inputbox font_NanumSquareRoundB" id="userpassword" name="user_pw">
	            </div>
	            <div class="mid-right font_NanumSquareRoundEB">
	                <button type="submit" class="btn btn-primary login-btn font_NanumSquareRoundEB">로그인</button>
	            </div>
	        </div>
        </form>
        <div class="bottom font_NanumSquareRoundEB">
            <button type="button" onclick="location.href='<%=Directory.REGISTER.getDirFull()%>'" class="btn btn-secondary font_NanumSquareRoundEB">회원가입</button>
            <button type="button" onclick="location.href='<%=Directory.FINDID.getDirFull()%>'" class="btn btn-secondary font_NanumSquareRoundEB">아이디 찾기</button>
            <button type="button" onclick="location.href='<%=Directory.FINDPW.getDirFull()%>'" class="btn btn-secondary font_NanumSquareRoundEB">비밀번호 재설정</button>
        </div>
    </div>
    <%@ include file="./Footer.jsp" %>
</body>
</html>