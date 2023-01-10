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
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <link rel="stylesheet" href="../src/css/style.css">
    <link rel="stylesheet" href="../src/css/Findid.css?after">
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
	<form action="./findId.han" method="post">
	    <div class="container">
	        <h2 class="font_Jua">아이디 찾기</h2>
	        <div class="form">
	            <label for="user_name" class="font_NanumSquareRoundEB">이름 <span class="red">* 　　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="user_name" name="user_name" required="required" maxlength="10">
	            <br>
	            <label for="userPhone" class="font_NanumSquareRoundEB">연락처 <span class="red">* 　</span></label>
	            <input type="text" class="phonebox font_NanumSquareRoundB" id="userPhone" name="userPhone1" required="required" v-model="userPhone1" @keyup="numberCheck1" maxlength="3" minlength="3"><span class="font_Jua"> - </span>
	            <input type="text" class="phonebox font_NanumSquareRoundB" name="userPhone2" required="required" v-model="userPhone2" @keyup="numberCheck2" maxlength="4" minlength="4"><span class="font_Jua"> - </span>
	            <input type="text" class="phonebox font_NanumSquareRoundB" name="userPhone3" required="required" v-model="userPhone3" @keyup="numberCheck3" maxlength="4" minlength="4">
	        </div>
	        <button type="submit" class="btn btn-secondary font_HannaPro searchbtn"><p class="font_HannaPro">아이디 찾기</p></button>
	    </div>
	</form>
	<script>
		let app = new Vue({
			el: '.container',
			data() {
			    return {
			    	userPhone1: '',
					userPhone2: '',
					userPhone3: '',
			    }
			},
			computed: {
			},
			methods: {
				numberCheck1() { 
			    	if (this.userPhone1.length != 0 && !(/^[0-9]+$/.test(this.userPhone1))) {
			    		alert('숫자만 입력할 수 있습니다');
			    		this.userPhone1 = '';
			    	}
			    },
			    numberCheck2() { 
			    	if (this.userPhone2.length != 0 && !(/^[0-9]+$/.test(this.userPhone2))) {
			    		alert('숫자만 입력할 수 있습니다');
			    		this.userPhone2 = '';
			    	}
			    },
			    numberCheck3() { 
			    	if (this.userPhone3.length != 0 && !(/^[0-9]+$/.test(this.userPhone3))) {
			    		alert('숫자만 입력할 수 있습니다');
			    		this.userPhone3 = '';
			    	}
			    },
			},
		});
	</script>
    <%@ include file="./Footer.jsp" %>
</body>
</html>