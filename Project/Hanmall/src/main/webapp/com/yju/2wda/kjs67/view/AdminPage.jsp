<%@page import="java.util.ArrayList"%>
<%@page import="kjs67_hanmall.model.ProductDTO"%>
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
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/style.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/AdminPage.css">
    <link rel="stylesheet"
		href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
	<link rel="stylesheet"
		href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
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
	
	ArrayList<LoginDTO> userList = (ArrayList<LoginDTO>) request.getAttribute("userList");
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
        <table class="table table-striped font_NanumSquareRoundB">
            <thead>
                <tr>
                    <th scope="col">아이디</th>
                    <th scope="col">이름</th>
                    <th scope="col">휴대폰</th>
                    <th scope="col">이메일</th>
                    <th scope="col">가입일</th>
                    <th scope="col">회원등급</th>
                    <th scope="col">탈퇴</th>
                </tr>
            </thead>
            <tbody>
            	<%
            	for (LoginDTO user : userList) {
            		%>
            		<tr>
	                    <td><%=user.getUser_id()%></td>
	                    <td><%=user.getUser_name()%></td>
	                    <td><%=user.getUser_phone()%></td>
	                    <td><%=user.getUser_mail()%></td>
	                    <td><%=user.getUser_date()%></td>
	                    <td>
	                    <%if (user.getUser_class() == 1) { // 해당 회원이 일반 회원이면
	                    	%>
		                    <select onchange="location.href=(this.value)" class="userclass-selectbox">
		                        <option value="./changeUserClass.han?user_num=<%=user.getUser_num()%>&newClass=<%=1%>" selected>일반회원</option>
		                        <option value="./changeUserClass.han?user_num=<%=user.getUser_num()%>&newClass=<%=2%>">판매자</option>
		                        <option value="./changeUserClass.han?user_num=<%=user.getUser_num()%>&newClass=<%=9%>">관리자</option>
		                    </select>
	                    	<%
	                    } else if (user.getUser_class() == 2) { // 해당 회원이 판매자면
	                    	%>
		                    <select onchange="location.href=(this.value)" class="userclass-selectbox">
		                        <option value="./changeUserClass.han?user_num=<%=user.getUser_num()%>&newClass=<%=1%>">일반회원</option>
		                        <option value="./changeUserClass.han?user_num=<%=user.getUser_num()%>&newClass=<%=2%>" selected>판매자</option>
		                        <option value="./changeUserClass.han?user_num=<%=user.getUser_num()%>&newClass=<%=9%>">관리자</option>
		                    </select>
	                    	<%
	                    } else if (user.getUser_class() == 9) { // 해당 회원이 관리자면 변경 못하게
	                    	%>
		                    <select disabled class="userclass-selectbox">
		                        <option>관리자　</option>
		                    </select>
	                    	<%
	                    }%>
	                    </td>
	                    <td>
	                    <%
	                    if (user.getUser_class() == 9) { // 해당 회원이 관리자면 삭제 못하게
	                    	%>
	                    	<button type="button" class="btn btn-outline-secondary" disabled>삭제</button>
	                    	<%
	                    } else {
	                    	%>
	                    	<form action="./deleteUserWithAdmin.han" method="post">
	                    		<input type="hidden" name="user_num" value="<%=user.getUser_num()%>">
		                    	<button type="submit" class="btn btn-outline-secondary">삭제</button>
	                    	</form>
	                    	<%
	                    }%>
	                    </td>
	                </tr>
            		<%
            	}
            	%>
            	<!-- 디자인용 샘플 코드 -->
                <!-- <tr>
                    <th>ique45</th>
                    <td>김정수</td>
                    <td>01028287305</td>
                    <td>ique45@naver.com</td>
                    <td>2022.12.08</td>
                    <td>
                        <select value="">
                            <option name="" id="">일반회원</option>
                            <option name="" id="">판매자</option>
                            <option name="" id="">관리자</option>
                        </select>
                    </td>
                    <td><button type="button" class="btn btn-outline-secondary">삭제</button></td>
                </tr>
                <tr>
                    <th>ique45</th>
                    <td>김정수</td>
                    <td>01028287305</td>
                    <td>ique45@naver.com</td>
                    <td>2022.12.08</td>
                    <td>
                        <select value="">
                            <option name="" id="">일반회원</option>
                            <option name="" id="">판매자</option>
                            <option name="" id="">관리자</option>
                        </select>
                    </td>
                    <td><button type="button" class="btn btn-outline-secondary">삭제</button></td>
                </tr>
                <tr>
                    <th>ique45</th>
                    <td>김정수</td>
                    <td>01028287305</td>
                    <td>ique45@naver.com</td>
                    <td>2022.12.08</td>
                    <td>
                        <select value="">
                            <option name="" id="">일반회원</option>
                            <option name="" id="">판매자</option>
                            <option name="" id="">관리자</option>
                        </select>
                    </td>
                    <td><button type="button" class="btn btn-outline-secondary">삭제</button></td>
                </tr> -->
            </tbody>
        </table>
    </div>
</body>
</html>