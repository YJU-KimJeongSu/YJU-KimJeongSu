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
	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
		crossorigin="anonymous">
	<link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/style.css">
	<link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/ProductDetail.css">
	<link rel="stylesheet"
		href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
	<link rel="stylesheet"
		href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
		crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
	<title>Document</title>
</head>

<body>
    <%
	LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
	// if (loginInfo != null) System.out.println("index : now user class : " + loginInfo.getUser_class());
	ProductDTO productInfo = (ProductDTO) request.getAttribute("productInfo");
	// System.out.println("ProductDetail : productInfo : " + productInfo);
	int user_class = 0;
	int user_num = 0;
	if (loginInfo != null) {
		user_class = loginInfo.getUser_class();
		user_num = loginInfo.getUser_num();
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
		<div class="left">
			<div id="carouselExampleIndicators" class="carousel slide"
				data-bs-ride="true">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="0" class="active" aria-current="true"
						aria-label="Slide 1"></button>
					<%
					if (productInfo.getProd_image1() != null) {
						%>
						<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="1" class="active" aria-current="true"
						aria-label="Slide 2"></button>
						<%
					}
					if (productInfo.getProd_image2() != null) {
						%>
						<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="2" class="active" aria-current="true"
						aria-label="Slide 3"></button>
						<%
					}
					if (productInfo.getProd_image3() != null) {
						%>
						<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="3" class="active" aria-current="true"
						aria-label="Slide 4"></button>
						<%
					}
					if (productInfo.getProd_image4() != null) {
						%>
						<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="4" class="active" aria-current="true"
						aria-label="Slide 5"></button>
						<%
					}
					if (productInfo.getProd_image5() != null) {
						%>
						<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="5" class="active" aria-current="true"
						aria-label="Slide 6"></button>
						<%
					}
					%>
				</div>
				<div class="carousel-inner slider"
					style="width: 40vw; height: 40vw;">
					<%-- <div class="carousel-item active">
						<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image0()%>" class="d-block w-100" alt="..." style="width: 40vw; height: 40vw;">
					</div>
					<div class="carousel-item">
						<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image0()%>" class="d-block w-100" alt="..." style="width: 40vw; height: 40vw;">
					</div>
					<div class="carousel-item">
						<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image0()%>" class="d-block w-100" alt="..." style="width: 40vw; height: 40vw;">
					</div> --%>
					<div class="carousel-item active">
						<img
							src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image0()%>"
							class="d-block w-100" alt="..."
							style="width: 40vw; height: 40vw;">
					</div>
					<%
					if (productInfo.getProd_image1() != null) {
					%>
					<div class="carousel-item">
						<img
							src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image1()%>"
							class="d-block w-100" alt="..."
							style="width: 40vw; height: 40vw;">
					</div>
					<%
					}
					if (productInfo.getProd_image2() != null) {
					%>
					<div class="carousel-item">
						<img
							src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image2()%>"
							class="d-block w-100" alt="..."
							style="width: 40vw; height: 40vw;">
					</div>
					<%
					}
					if (productInfo.getProd_image3() != null) {
					%>
					<div class="carousel-item">
						<img
							src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image3()%>"
							class="d-block w-100" alt="..."
							style="width: 40vw; height: 40vw;">
					</div>
					<%
					}
					if (productInfo.getProd_image4() != null) {
					%>
					<div class="carousel-item">
						<img
							src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image4()%>"
							class="d-block w-100" alt="..."
							style="width: 40vw; height: 40vw;">
					</div>
					<%
					}
					if (productInfo.getProd_image5() != null) {
					%>
					<div class="carousel-item">
						<img
							src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=productInfo.getProd_image5()%>"
							class="d-block w-100" alt="..."
							style="width: 40vw; height: 40vw;">
					</div>
					<%
					}
					%>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
			<div class="product-detail">
				<%=productInfo.getProd_content()%>
			</div>
		</div>
		<div class="right">
			<div class="card" style="width: 40vw; position: fixed;">
				<div class="card-body">
					<p class="font_NanumSquareRoundR"><%=productInfo.getProd_cate1()%> > <%=productInfo.getProd_cate2()%>번 카테고리</p>
					<p class="font_NanumSquareRoundR">상품코드 : <%=productInfo.getProd_num()%></p>
					<h2 class="font_NanumSquareRoundB"><%=productInfo.getProd_name()%></h2>
					<h3 class="font_NanumSquareRoundB" style="margin: 1vw 0vw;"><%=productInfo.getProd_price()%>원</h3>
					<%-- <h4 class="font_NanumSquareRoundB">
						평점 <span class="font_Jua"><%=productInfo.getProd_grade()%></span>
					</h4> --%>
					<p class="font_NanumSquareRoundR">
						<%=productInfo.getProd_summary()%>
					</p>
					<div class="count-form font_NanumSquareRoundR">
						<div class="count-form-left">
							<p class="font_NanumSquareRoundB">구매수량</p>
						</div>
						<div class="count-form-right">
							<button @click="countMinus()">
								<p class="font_Jua">-</p>
							</button>
							<input type="number" v-model="buyCount" name="buyCount" disabled>
								<button @click="countPlus()">
								<p class="font_Jua">+</p>
							</button>
						</div>
					</div>
					<p><b>총액 : {{totalPrice}}원</b></p>
						<div class="buttons font_NanumSquareRoundB">
							<p v-if="user_num==0" style="color: red; align-self: center;">로그인 후 이용해주세요.</p>
							<form action="./addToCart.han" method="post" class="buttons">
								<input type="hidden" name="prod_num" value="<%=productInfo.getProd_num()%>">
								<input type="hidden" v-model="buyCount" name="buyCount">
								<button v-if="user_num!=0 & buyCount > 0 & <%=productInfo.getProd_count()%> > 0" type="submit" class="btn btn-secondary">장바구니</button>
								<button v-if="user_num!=0 & buyCount == 0 & <%=productInfo.getProd_count()%> > 0" type="button" class="btn btn-secondary" @click="notCart()">장바구니</button>
							</form>
							<form action="./pay.han" method="post" class="buttons">
								<input type="hidden" name="prod_num" value="<%=productInfo.getProd_num()%>">
								<input type="hidden" v-model="buyCount" name="buyCount">
								<button v-if="user_num!=0 & buyCount > 0 & <%=productInfo.getProd_count()%> > 0" type="submit" class="btn btn-secondary">구매하기</button>
								<button v-if="user_num!=0 & buyCount == 0 & <%=productInfo.getProd_count()%> > 0" type="button" class="btn btn-secondary" @click="notBuy()">구매하기</button>
							</form>
							<p v-if="user_num!=0 & <%=productInfo.getProd_count()%> <= 0" style="color: red; align-self: center;">상품 재고가 없습니다.</p>
						</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		let app = new Vue({
		    el: '.container',
		    data() {
		        return {
					buyCount: 0,
					user_num: <%=user_num%>,
		        }
		    },
		    computed: {
		    	totalPrice() {
		    		return this.buyCount * <%=productInfo.getProd_price()%>;
		    	}
		    },
		    methods: {
		    	countPlus() {
		    		if (this.buyCount < <%=productInfo.getProd_count()%>) this.buyCount++;
		    		else alert("재고가 부족합니다.");
		    	},
		    	countMinus() {
		    		if (this.buyCount > 0) this.buyCount--;
		    	},
		    	notBuy() {
		    		alert('0개는 구매할 수 없습니다.');
		    	},
		    	notCart() {
		    		alert('0개는 담을 수 없습니다.');
		    	},
		    },
		});
	</script>
	<%@ include file="./Footer.jsp" %>
</body>
</html>