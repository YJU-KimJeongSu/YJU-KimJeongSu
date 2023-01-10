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
    <link rel="stylesheet" href="../src/css/Register.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
    <script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
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
    <form action="./register.han" method="post">
    	<div class="container">
	        <div class="regi-form">
	            <h2 class="top font_Jua">회원 가입</h2>
	            <label for="userID" class="font_NanumSquareRoundEB">아이디 <span class="red">* 　　　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="userID" name="userID" required="required" v-model="userID" minlength="5" maxlength="20" @keyup="idCheck()">
	            <span class="font_Jua idCheckText" style="color: gray; display: inline-block;">{{idCheckResult}}<p id="idChecker" style="display: inline-block; margin: 0px; padding: 0px;"></p></span>
	            <br>
	            <label for="userPw" class="font_NanumSquareRoundEB">비밀번호 <span class="red">* 　　</span></label>
	            <input type="password" class="inputbox font_NanumSquareRoundB" id="userPw" name="userPw" required="required" v-model="userPw" @keyup="pwCheck()" minlength="5" maxlength="30">
	            <br>
	            <label for="userPwCheck" class="font_NanumSquareRoundEB">비밀번호 확인 <span class="red">* </span></label>
	            <input type="password" class="inputbox font_NanumSquareRoundB" id="userPwCheck" name="userPwCheck" required="required" v-model="userPwCheck" @keyup="pwCheck()">
	            <span class="font_Jua" style="color: gray;">{{pwCheckResult}}</span>
	            <br>
	            <label for="userName" class="font_NanumSquareRoundEB">이름 <span class="red">* 　　　　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="userName" name="userName" required="required" v-model="userName" maxlength="10">
	            <br>
	            <label for="userPhone" class="font_NanumSquareRoundEB">연락처 <span class="red">* 　　　</span></label>
	            <input type="text" class="phonebox font_NanumSquareRoundB" id="userPhone" name="userPhone1" required="required" v-model="userPhone1" @keyup="numberCheck1" maxlength="3" minlength="3"><span class="font_Jua"> - </span>
	            <input type="text" class="phonebox font_NanumSquareRoundB" name="userPhone2" required="required" v-model="userPhone2" @keyup="numberCheck2" maxlength="4" minlength="4"><span class="font_Jua"> - </span>
	            <input type="text" class="phonebox font_NanumSquareRoundB" name="userPhone3" required="required" v-model="userPhone3" @keyup="numberCheck3" maxlength="4" minlength="4">
	            <br>
	            <label for="userMail" class="font_NanumSquareRoundEB">이메일 <span class="red">* 　　　</span></label>
	            <input type="email" class="emailbox font_NanumSquareRoundB" id="userMail" name="userMail" required="required" v-model="userMail" maxlength="50">
	            <br>
	            <label for="userZip" class="font_NanumSquareRoundEB">주소 <span class="red">* 　　　　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="userZip" placeholder="우편번호" maxlength="5" name="userZip" required="required" v-model="userZip" @keyup="zipCheck()">
	            <br>
	            <label for="userAddr" class="font_NanumSquareRoundEB hidden">히든 <span class="red">* 　　　　</span></label>
	            <input type="text" class="addrbox font_NanumSquareRoundB" id="userAddr" placeholder="주소" name="userAddr" required="required" v-model="userAddr" maxlength="100">
	            <br>
	            <label for="userDAddr" class="font_NanumSquareRoundEB hidden">히든 <span class="red">* 　　　　</span></label>
	            <input type="text" class="addrbox font_NanumSquareRoundB" id="userDAddr" placeholder="상세주소" name="userDAddr" required="required" v-model="userDAddr" maxlength="100">
	            <br>
	        </div>
        		<p v-if="canReg == false" class="red font_Jua">모든 항목을 입력해주세요.</p>
        	<div class="btns font_Jua">
            	<button type="button" class="btn btn-light font_Jua" onclick="location.href='<%=Directory.INDEX.getDirFull()%>'">취소하기</button>
            	<button v-if="canReg" type="submit" class="btn btn-primary font_Jua" id="regBtn">가입하기</button>
            	<button v-else type="submit" class="btn btn-primary font_Jua" disabled>가입하기</button>
        	</div>
    	</div>
    </form>
    <script>
    function checkIdAjax() {
    	$('#idChecker').css("display", "inline-block");
        var id = $('#userID').val();
        $.ajax({
            url:'./idCheck.han', //Controller에서 요청 받을 주소
            type:'post', //POST 방식으로 전달
            data:{id:id},
            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
                if (cnt == 0){
                    $('#idChecker').text('사용가능한 아이디입니다');
                	$('#regBtn').attr("disabled", false);
                } else {
                	$('#idChecker').text('중복된 아이디입니다');
                	$('#regBtn').attr("disabled", true);
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
    };
    
    function hideText() {
    	$('#idChecker').css("display", "none");
    }
    
	let app = new Vue({
		el: '.container',
		data() {
		    return {
				userID: '',
				userPw: '',
				userPwCheck: '',
				userName: '',
				userPhone1: '',
				userPhone2: '',
				userPhone3: '',
				userMail: '',
				userZip: '',
				userAddr: '',
				userDAddr: '',
				
				hiddenChecker: '',
				idCheckResult: '',
				pwCheckResult: '',
		    }
		},
		computed: {
			canReg() {
				if (this.userID != ''
						&& this.userPw != ''
						&& this.userPwCheck != ''
						&& this.userName != ''
						&& this.userPhone1 != ''
						&& this.userPhone2 != ''
						&& this.userPhone3 != ''
						&& this.userMail != ''
						&& this.userZip != ''
						&& this.userAddr != ''
						&& this.userDAddr != ''
						&& this.idCheckResult == ''
						&& this.pwCheckResult == '비밀번호가 일치합니다') {
					return true;
				} else {
					return false;
				}
			},
		},
		methods: {
		    pwCheck() {
		    	if (this.userPw.length >= 5 && this.userPwCheck.length >= 5) {
			    	if (this.userPw == this.userPwCheck) {
			    		this.pwCheckResult = '비밀번호가 일치합니다'
			    	} else {
			    		this.pwCheckResult = '비밀번호가 일치하지 않습니다'
			    	}
		    	} else {
		    		this.pwCheckResult = '비밀번호가 너무 짧습니다'
		    	}
		    	
		    },
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
		    idCheck() { 
		    	if (!(/^[0-9a-zA-Z]+$/.test(this.userID))) {
		    		hideText();
		    		this.idCheckResult = '영어와 숫자만 사용할 수 있습니다.';
		    	} else if (this.userID.length < 5) {
		    		hideText();
		    		this.idCheckResult = '5글자 이상 입력해주세요.';
		    	} else {
		    		this.idCheckResult = '';
		    		checkIdAjax();
		    	}
		    },
		    zipCheck() { 
		    	if (this.userZip.length != 0 && !(/^[0-9]+$/.test(this.userZip))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.userZip = '';
		    	}
		    },
		 },
	});
</script>
    <%@ include file="./Footer.jsp" %>
</body>
</html>