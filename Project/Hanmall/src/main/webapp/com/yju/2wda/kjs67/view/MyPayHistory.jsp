<%@page import="kjs67_hanmall.model.PaymentDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kjs67_hanmall.model.LoginDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/style.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Pay.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
	<link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%
	LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
	// if (loginInfo != null) System.out.println("index : now user class : " + loginInfo.getUser_class());
	
	ArrayList<PaymentDTO> paymentList = (ArrayList<PaymentDTO>) request.getAttribute("paymentList");
//	for (PaymentDTO t : paymentList) {
//		System.out.println("MyPayHistory.jsp : paymentList : " + t.toString());
//	}
	
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
		<button class="btn btn-secondary topbtn font_NanumSquareRoundEB" onclick="location.href='<%=Directory.PASSWORDCHECK.getDirFull()%>'">회원 정보 수정</button>
		<button class="btn btn-secondary topbtn font_NanumSquareRoundEB" disabled>구매 이력 조회</button>
	</div>
	<div class="container">
		<div class="font_NanumSquareRoundEB" style="width: 100%; max-width: 1100px; background-color: #ddd;">
			<table class="table table-striped">
				<thead style="background-color: darkgray;">
				    <tr>
				    	<th scope="col">주문번호</th>
				    	<th scope="col">배송지</th>
				    	<th scope="col">결제금액</th>
				    	<th scope="col">결제일</th>
				    </tr>
				</thead>
				<tbody>
					<%
					for (PaymentDTO p : paymentList) {
						String payDate = p.getPay_date().substring(0, 4) + "." + p.getPay_date().substring(4, 6) + "." + p.getPay_date().substring(6, 8);
						%>
						<tr>
							<td><%=p.getOrder_id()%></td>
							<td><%=p.getDeliv_addr()%> <%=p.getDeliv_detail_addr()%></td>
							<td><%=p.getPay_amount()%></td>
							<td><%=payDate%></td>
						</tr>
						<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="./Footer.jsp" %>
</body>
</html>