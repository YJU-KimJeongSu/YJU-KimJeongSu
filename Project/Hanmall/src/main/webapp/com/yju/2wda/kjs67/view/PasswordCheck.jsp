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
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
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
	<div class="menu-navi-btn font_NanumSquareRoundEB">
		<button class="btn btn-secondary topbtn font_NanumSquareRoundEB" disabled>회원 정보 수정</button>
		<button class="btn btn-secondary topbtn font_NanumSquareRoundEB" onclick="location.href='./myPayHistory.han'">구매 이력 조회</button>
	</div>
	
    <div class="container">
        <div class="top font_Jua" style="padding-top: 2vw;">
            <h2>비밀번호 확인</h2>
        </div>
        <%-- <form method="post" action="<%=Directory.EDITUSERINFO.getDirFull()%>"> --%>
        <form method="post" action="./pwCheck.han">
	        <div class="mid" style="padding-bottom: 2vw;">
	            <div class="mid-left">
	                <label for="userid" class="font_NanumSquareRoundEB">비밀번호 　　</label>
	                <input type="password" class="inputbox font_NanumSquareRoundB" id="userpassword" name="user_pw" v-model="user_pw">
	                <br>
	                <label for="userpassword2" class="font_NanumSquareRoundEB">비밀번호 확인</label>
	                <input type="password" class="inputbox font_NanumSquareRoundB" id="userpassword2" v-model="user_pw_check">
	            </div>
	            <div class="mid-right font_NanumSquareRoundEB">
	                <button v-if="pwCheckState == 0" type="button" class="btn btn-primary login-btn font_NanumSquareRoundEB" disabled>확인</button>
	                <button v-else-if="pwCheckState == 1" type="button" class="btn btn-primary login-btn font_NanumSquareRoundEB" @click="noSubmit()">확인</button>
	                <button v-else-if="pwCheckState == 2" type="submit" class="btn btn-primary login-btn font_NanumSquareRoundEB">확인</button>
	            </div>
	        </div>
        </form>
        <div class="bottom font_NanumSquareRoundEB">
        </div>
    </div>
    <script>
	let app = new Vue({
		el: '.container',
		data() {
		    return {
				user_pw: '',
				user_pw_check: '',
		    }
		},
		computed: {
			pwCheckState() {
				if (this.user_pw == '' || this.user_pw_check == '') {
					return 0; // 하나라도 입력 안하면 비활성화 버튼 보여주기
				} else if (this.user_pw != this.user_pw_check) {
					return 1; // 비밀번호랑 비밀번호 확인이 서로 다르면 제출 안되는 버튼 보여주기
				} else if (this.user_pw == this.user_pw_check && this.user_pw != '<%=loginInfo.getUser_pw()%>') {
					return 2; // 인풋 2개는 제대로 입력
				}
			}
		},
		methods: {
			noSubmit() {
				alert('비밀번호와 비밀번호 확인이 서로 일치하지 않습니다.');
				this.user_pw = '';
				this.user_pw_check = '';
			},
		 },
	});
</script>
    <%@ include file="./Footer.jsp" %>
</body>
</html>