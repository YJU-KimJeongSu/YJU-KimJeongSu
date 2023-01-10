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
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Cart.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
	<link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <title>Document</title>
</head>
<body>
	<%
	LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
	
	int user_class = 0;
	if (loginInfo != null) {
		user_class = loginInfo.getUser_class();
	}
	
	ArrayList<ProductDTO> cartList = new ArrayList<ProductDTO>();
	cartList = (ArrayList<ProductDTO>) request.getAttribute("cartList");
	/* for (ProductDTO p : cartList) {
		System.out.println("Cart : cartList.product : " + p);
	} */
	
	int cartSize = 0;
	if (cartList != null) {
		cartSize = cartList.size();
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
	<div class="app">
	    <div class="container" v-if="!isCartEmpty">
	        <div class="product-list">
	        	<%
	        	for (ProductDTO product : cartList) {
	        		%>
	        	<div class="product">
	                <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/thumb/<%=product.getProd_thumb_200()%>" class="product_image">
	                <p class="product_title font_NanumSquareRoundEB"><%=product.getProd_name()%></p>
	                <p class="product_price font_NanumSquareRoundB"><%=product.getProd_price()%>원<br><%=product.getBuyCount()%>개</p>
	                <form action="./cartRemove.han" method="post">
	                	<input type="hidden" name="cart_num" value="<%=product.getCart_num()%>">
	                	<input type="submit" value="삭제" class="deleteBtn font_Jua">
	                </form>
	            </div>
	        		<%
	        	}
	        	%>
	        	<!-- 디자인용 샘플 코드 -->
	            <%-- <div class="product">
	                <img src="../src/image/product.png" class="product_image">
	                <p class="product_title font_NanumSquareRoundEB">대충 상품명</p>
	                <p class="product_price font_NanumSquareRoundB">가격 100원</p>
	            </div>
	            <div class="product">
	                <img src="../src/image/product.png" class="product_image">
	                <p class="product_title font_NanumSquareRoundEB">마아아아아아ㅏㅇ아ㅏ아아아아아dkdkdkdkdk아ㅏ않이 긴 상품명</p>
	                <p class="product_price font_NanumSquareRoundB">가격 100000000000원</p>
	            </div>
	            <div class="product">
	                <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/thumb/<%=product.getProd_thumb_80()%>" class="product_image">
	                <p class="product_title font_NanumSquareRoundEB"><%=product.getProd_name()%></p>
	                <p class="product_price font_NanumSquareRoundB"><%=product.getProd_price()%>원<br><%=buyCount%>개</p>
	            </div> --%>
	            <!-- 디자인용 샘플 코드 -->
	            <form action="./cartListOrder.han" method="post">
		        	<div class="btns font_Jua">
			        	<input type="hidden" name="user_num" value="<%=loginInfo.getUser_num()%>">
			            <button type="submit" class="btn btn-primary font_Jua">주문하기</button>
			        </div>
	            </form>
	        </div>
	    </div>
	    <div class="container" v-else>
	    	<div class="blank-page font_Jua">
	    		<p>장바구니가 비어있습니다.</p>
	    	</div>
	    </div>
    </div>
    <script>
	let app = new Vue({
		el: '.app',
		data() {
		    return {
		    	isCartEmpty: <%=(cartSize > 0) ? false : true%>, // 비어있으면 true, 아니면 false
		    }
		},
		computed: {
			
		},
		methods: {
		    
		 },
	});
</script>
    <%@ include file="./Footer.jsp" %>
</body>
</html>