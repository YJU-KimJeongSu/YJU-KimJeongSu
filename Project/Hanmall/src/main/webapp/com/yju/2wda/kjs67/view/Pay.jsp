<%@page import="java.net.InetAddress"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Pay.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
	<link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
	<script src="https://js.tosspayments.com/v1/payment"></script>
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    <title>Document</title>
</head>
<body>
	<%
	InetAddress inet= InetAddress.getLocalHost();
	// System.out.println(inet.getHostAddress());
	LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
	// ProductDTO product = (ProductDTO) request.getAttribute("product");
	ArrayList<ProductDTO> productList = (ArrayList<ProductDTO>) request.getAttribute("productList");
	
	// for (ProductDTO p : productList) {
	//	System.out.println("Pay.jsp : productList : " + productList);
	// }
	
	int buyCount = 0;
	if (request.getAttribute("buyCount") != null) {
		buyCount = (int)request.getAttribute("buyCount");
	}
	// System.out.println("Pay : loginInfo : " + loginInfo);
	// System.out.println("Pay : product : " + product);
	// System.out.println("Pay : buyCount : " + buyCount);
	
	int user_class = 0;
	if (loginInfo != null) {
		user_class = loginInfo.getUser_class();
	}
	
	/* 주문금액 총합 구하기 */
	int totalPrice = 0;
	for (ProductDTO product : productList) {
		// 상품 디테일 페이지에서 바로 넘어오면 Cart_count은 0이고, buyCount 적용
		// 카드에서 넘어오면 buyCount는 0이고, Cart_count 적용
		totalPrice += product.getProd_price() * product.getCart_count();
		totalPrice += product.getProd_price() * buyCount;
	}
	
	/* session으로 현재 상품 결제 페이지로 넘기기용 - 취소 */
	if (buyCount != 0) {
		productList.get(0).setCart_count(buyCount);
	}
	session.setAttribute("productOrderList", productList);
	// System.out.println("Pay.jsp : productList.get(0).getCart_count() : " + productList.get(0).getCart_count());
	// System.out.println("Pay.jsp : session's product : " + ((ArrayList<ProductDTO>) session.getAttribute("productOrderList")).get(0).getCart_count());
	
	/* orderId = user_num + product_num + '-' + product_ + currentTime */
	Date date = new Date();
	SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowTime = simple.format(date);
	// System.out.println(nowTime);
	
	String productIdStr = "";
	
	for (ProductDTO p : productList) {
		productIdStr += String.valueOf(p.getProd_num()) + "-";
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
	        <div class="product-list">
	        	<%
	        	if (buyCount == 0 ) { // 장바구니에서 넘어왔으면 getCart_count() 적용
		        	for (ProductDTO product : productList) {
		        		%>
		        		<div class="product">
			                <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/thumb/<%=product.getProd_thumb_80()%>" class="product_image">
			                <p class="product_title font_NanumSquareRoundEB"><%=product.getProd_name()%></p>
			                <p class="product_price font_NanumSquareRoundB"><%=product.getProd_price()%>원<br><%=product.getCart_count()%>개</p>
			            </div>
		        		<%
		        	}
	        	} else { // 상품상세 페이지에서 넘어왔으면 buyCount 적용
	        		ProductDTO product = productList.get(0);
	        		%>
	        			<div class="product">
			                <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/thumb/<%=product.getProd_thumb_80()%>" class="product_image">
			                <p class="product_title font_NanumSquareRoundEB"><%=product.getProd_name()%></p>
			                <p class="product_price font_NanumSquareRoundB"><%=product.getProd_price()%>원<br><%=buyCount%>개</p>
			            </div>
	        		<%
	        	}
	        	%>
	        
	            <%-- <div class="product">
	                <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/thumb/<%=product.getProd_thumb_80()%>" class="product_image">
	                <p class="product_title font_NanumSquareRoundEB"><%=product.getProd_name()%></p>
	                <p class="product_price font_NanumSquareRoundB"><%=product.getProd_price()%>원<br><%=buyCount%>개</p>
	            </div> --%>
	            <!-- <div class="product">
	                <img src="../src/image/product.png" class="product_image">
	                <p class="product_title font_NanumSquareRoundEB">대충 상품명</p>
	                <p class="product_price font_NanumSquareRoundB">가격 100원</p>
	            </div>
	            <div class="product">
	                <img src="../src/image/product.png" class="product_image">
	                <p class="product_title font_NanumSquareRoundEB">마아아아아아ㅏㅇ아ㅏ아아아아아dkdkdkdkdk아ㅏ않이 긴 상품명</p>
	                <p class="product_price font_NanumSquareRoundB">가격 100000000000원</p>
	            </div> -->
	        </div>
	        <div class="pay-amount">
	            <h2 class="font_NanumSquareRoundEB">주문 금액 총합 : <%=totalPrice%>원</h2>
	        </div>
	        <div class="buyer-info">
	            <div class="padding">
	                <h2 class="font_NanumSquareRoundEB">주문자 정보</h2>
	                <label for="buyerName" class="font_NanumSquareRoundEB">이름 <span class="red">*　</span></label>
	                <input type="text" class="inputbox font_NanumSquareRoundB" id="buyerName" v-model="user_name" required>
	                <br>
	                <label for="buyerPhone" class="font_NanumSquareRoundEB">연락처 <span class="red">*</span></label>
	                <input type="text" class="phonebox font_NanumSquareRoundB" id="buyerPhone" maxlength="3" v-model="user_phone1" required @keyup="numberCheck1()"><span class="font_NanumSquareRoundEB"> -</span>
	                <input type="text" class="phonebox font_NanumSquareRoundB" maxlength="4" v-model="user_phone2" required @keyup="numberCheck2()"><span class="font_NanumSquareRoundEB"> -</span>
	                <input type="text" class="phonebox font_NanumSquareRoundB" maxlength="4" v-model="user_phone3" required @keyup="numberCheck3()">
	                <br>
	                <label for="buyerZip" class="font_NanumSquareRoundEB">주소 <span class="red">*　</span></label>
	                <input type="text" class="inputbox font_NanumSquareRoundB" id="buyerZip" placeholder="우편번호" maxlength="5" v-model="user_zip" required @keyup="zipCheck1()">
	                <br>
	                <label for="buyerAddr" class="font_NanumSquareRoundEB hidden">히든 <span class="red">*　</span></label>
	                <input type="text" class="addrbox font_NanumSquareRoundB" id="buyerAddr" placeholder="주소" v-model="user_addr" required>
	                <br>
	                <label for="buyerDAddr" class="font_NanumSquareRoundEB hidden">히든 <span class="red">*　</span></label>
	                <input type="text" class="addrbox font_NanumSquareRoundB" id="buyerDAddr" placeholder="상세주소" v-model="user_detail_addr" required>
	                <br>
	            </div>
	        </div>
	        <div class="buyer-info">
	            <div class="padding">
	                <h2 class="font_NanumSquareRoundEB inline">배송 정보</h2>
	                <div class="inline">
	                    <!-- <input type="checkbox" id="check"> -->
	                    <input class="form-check-input" type="checkbox" value="" id="check" @click="sendToMe">
	                    <label for="check" class="font_NanumSquareRoundEB checklabel">주문자 정보와 같습니다</label>
	                    <br>
	                </div>
	                <label for="deliveryName" class="font_NanumSquareRoundEB">이름 <span class="red">*　</span></label>
	                <input type="text" class="inputbox font_NanumSquareRoundB" id="deliveryName" v-model="deliv_name" required>
	                <br>
	                <label for="deliveryPhone" class="font_NanumSquareRoundEB">연락처 <span class="red">*</span></label>
	                <input type="text" class="phonebox font_NanumSquareRoundB" id="deliveryPhone" maxlength="3"  v-model="deliv_phone1" required @keyup="numberCheck4()"><span class="font_NanumSquareRoundEB"> -</span>
	                <input type="text" class="phonebox font_NanumSquareRoundB" maxlength="4"  v-model="deliv_phone2" required @keyup="numberCheck5()"><span class="font_NanumSquareRoundEB"> -</span>
	                <input type="text" class="phonebox font_NanumSquareRoundB" maxlength="4"  v-model="deliv_phone3" required @keyup="numberCheck6()">
	                <br>
	                <label for="deliveryZip" class="font_NanumSquareRoundEB">주소 <span class="red">*　</span></label>
	                <input type="text" class="inputbox font_NanumSquareRoundB" id="deliveryZip" placeholder="우편번호" maxlength="5" v-model="deliv_zip" required @keyup="zipCheck2()">
	                <br>
	                <label for="deliveryAddr" class="font_NanumSquareRoundEB hidden">히든 <span class="red">*　</span></label>
	                <input type="text" class="addrbox font_NanumSquareRoundB" id="deliveryAddr" placeholder="주소" v-model="deliv_addr" required>
	                <br>
	                <label for="deliveryDAddr" class="font_NanumSquareRoundEB hidden">히든 <span class="red">*　</span></label>
	                <input type="text" class="addrbox font_NanumSquareRoundB" id="deliveryDAddr" placeholder="상세주소" v-model="deliv_detail_addr" required>
	                <br>
	            </div>
	        </div>
	        <div class="pay-methods font_NanumSquareRoundB">
	        	<h2 class="font_NanumSquareRoundEB inline" style="align-self: flex-start;">결제 방법</h2>
	        	<div class="form-check">
					<input class="form-check-input" type="radio" name="payMethods" id="kakaopay" required v-model="payMethods" value="kakaopay" onclick="kakaoWaring()">
					<label class="form-check-label" for="kakaopay">
						카카오페이　
					</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="payMethods" id="card" required v-model="payMethods" value="card">
					<label class="form-check-label" for="card">
						카드　
					</label>
				</div>
				<div class="form-check">
					<input class="form-check-input" type="radio" name="payMethods" id="phone" required v-model="payMethods" value="phone">
					<label class="form-check-label" for="phone">
						휴대폰 소액결제　
					</label>
				</div>
	        </div>
	        <div class="btns font_Jua">
	            <button type="button" class="btn btn-light font_Jua">취소하기</button>
	            <!-- <button type="button" class="btn btn-primary font_Jua" @click="order">주문하기</button> -->
	            <button type="button" class="btn btn-primary font_Jua" @click="order()">주문하기</button>
	        </div>
	    </div>
    <script>
    var clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq';
    var tossPayments = TossPayments(clientKey);
    
    // db insert용
    let amount = <%=totalPrice%>;
    let orderId = '<%=loginInfo.getUser_num()%>-<%=productIdStr%><%=nowTime%>';
    let date = '<%=nowTime%>';
    
    function tossPay() {
    	// 결제 요청 전에 db에 insert하는 방식에서, 결제 완료 후 db에 insert하는 방식으로 변경 
        /* $.ajax({
            url:'./prePayment.han', //Controller에서 요청 받을 주소
            type:'post', //POST 방식으로 전달
            data: {
            	user_num: app.user_num,
            	user_name: app.user_name,
            },
            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
            },
            error:function(){
                alert("에러입니다");
            }
        }); */
        
    	tossPayments.requestPayment('카드', { // 결제 수단 파라미터
	  		// 결제 정보 파라미터
	  		amount: amount,
	  		orderId: orderId,
	  		orderName: '토스 티셔츠 외 2건', // 이용하지 않음
	  		customerName: '박토스', // 이용하지 않음
	  		successUrl: 'http://<%=inet.getHostAddress()%>:8081/Hanmall/productOrderSuccess.han?date=' + date + "&method=tossPay"
			  		+ "&user_num=" + app.user_num + "&user_name=" + app.user_name + "&user_phone1=" + app.user_phone1 + "&user_phone2=" + app.user_phone2 + "&user_phone3=" + app.user_phone3
		    		+ "&user_zip=" + app.user_zip + "&user_addr=" + app.user_addr + "&user_detail_addr=" + app.user_detail_addr
		    		+ "&deliv_name=" + app.deliv_name + "&deliv_phone1=" + app.deliv_phone1 + "&deliv_phone2=" + app.deliv_phone2 + "&deliv_phone3=" + app.deliv_phone3
		    		+ "&deliv_zip=" + app.deliv_zip + "&deliv_addr=" + app.deliv_addr + "&deliv_detail_addr=" + app.deliv_detail_addr,
		    failUrl: 'http://<%=inet.getHostAddress()%>:8081/Hanmall/productOrderFail.han?date=' + date + "&method=tossPay"
			  		+ "&user_num=" + app.user_num + "&user_name=" + app.user_name + "&user_phone1=" + app.user_phone1 + "&user_phone2=" + app.user_phone2 + "&user_phone3=" + app.user_phone3
		    		+ "&user_zip=" + app.user_zip + "&user_addr=" + app.user_addr + "&user_detail_addr=" + app.user_detail_addr
		    		+ "&deliv_name=" + app.deliv_name + "&deliv_phone1=" + app.deliv_phone1 + "&deliv_phone2=" + app.deliv_phone2 + "&deliv_phone3=" + app.deliv_phone3
		    		+ "&deliv_zip=" + app.deliv_zip + "&deliv_addr=" + app.deliv_addr + "&deliv_detail_addr=" + app.deliv_detail_addr,
	  		validHours: 24,
	  		cashReceipt: {
	  			type: '소득공제',
	  		},
	  	})
	}
    
    function phonePay() {
    	tossPayments.requestPayment('휴대폰', { // 결제 수단 파라미터
	  		// 결제 정보 파라미터
	  		amount: amount,
	  		orderId: orderId,
	  		orderName: '토스 티셔츠 외 2건', // 이용하지 않음
	  		customerName: '박토스', // 이용하지 않음
	  		successUrl: 'http://<%=inet.getHostAddress()%>:8081/Hanmall/productOrderSuccess.han?date=' + date + "&method=phone"
			  		+ "&user_num=" + app.user_num + "&user_name=" + app.user_name + "&user_phone1=" + app.user_phone1 + "&user_phone2=" + app.user_phone2 + "&user_phone3=" + app.user_phone3
		    		+ "&user_zip=" + app.user_zip + "&user_addr=" + app.user_addr + "&user_detail_addr=" + app.user_detail_addr
		    		+ "&deliv_name=" + app.deliv_name + "&deliv_phone1=" + app.deliv_phone1 + "&deliv_phone2=" + app.deliv_phone2 + "&deliv_phone3=" + app.deliv_phone3
		    		+ "&deliv_zip=" + app.deliv_zip + "&deliv_addr=" + app.deliv_addr + "&deliv_detail_addr=" + app.deliv_detail_addr,
		    failUrl: 'http://<%=inet.getHostAddress()%>:8081/Hanmall/productOrderFail.han?date=' + date + "&method=phone"
			  		+ "&user_num=" + app.user_num + "&user_name=" + app.user_name + "&user_phone1=" + app.user_phone1 + "&user_phone2=" + app.user_phone2 + "&user_phone3=" + app.user_phone3
		    		+ "&user_zip=" + app.user_zip + "&user_addr=" + app.user_addr + "&user_detail_addr=" + app.user_detail_addr
		    		+ "&deliv_name=" + app.deliv_name + "&deliv_phone1=" + app.deliv_phone1 + "&deliv_phone2=" + app.deliv_phone2 + "&deliv_phone3=" + app.deliv_phone3
		    		+ "&deliv_zip=" + app.deliv_zip + "&deliv_addr=" + app.deliv_addr + "&deliv_detail_addr=" + app.deliv_detail_addr,
	  		validHours: 24,
	  		cashReceipt: {
	  			type: '소득공제',
	  		},
	  	})
	}
    
    function kakaopay() {
    	$(function(){
            var IMP = window.IMP; // 생략가능
            IMP.init('imp34000714'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
            var msg;
            
            IMP.request_pay({
                pg : 'kakaopay',
                pay_method : 'card',
                orderId: orderId,
                merchant_uid : 'merchant_' + new Date().getTime(),
                name : 'Hanmall',
                amount : amount,
                buyer_email : 'ique45@naver.com',
                buyer_name : '김정수',
                buyer_tel : '01028287305',
                buyer_addr : '대구광역시 달서구',
                buyer_postcode : '12345',
                //m_redirect_url : 'http://www.naver.com'
            }, function(rsp) {
                if ( rsp.success ) {
                    //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                    jQuery.ajax({
                        url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                        type: 'POST',
                        dataType: 'json',
                        data: {
                            imp_uid : rsp.imp_uid
                            //기타 필요한 데이터가 있으면 추가 전달
                        }
                    }).done(function(data) {
                        //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                        if ( everythings_fine ) {
                            msg = '결제가 완료되었습니다.';
                            msg += '\n고유ID : ' + rsp.imp_uid;
                            msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                            msg += '\결제 금액 : ' + rsp.paid_amount;
                            msg += '카드 승인번호 : ' + rsp.apply_num;
                            
                            alert(msg);
                        } else {
                            //[3] 아직 제대로 결제가 되지 않았습니다.
                            //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                        }
                    });
                    //성공시 이동할 페이지
                    location.href='http://<%=inet.getHostAddress()%>:8081/Hanmall/productOrderSuccess.han?date=' + date + "&orderId=" + orderId + "&amount=" + amount + "&method=kakaoPay"
		            		+ "&user_num=" + app.user_num + "&user_name=" + app.user_name + "&user_phone1=" + app.user_phone1 + "&user_phone2=" + app.user_phone2 + "&user_phone3=" + app.user_phone3
		            		+ "&user_zip=" + app.user_zip + "&user_addr=" + app.user_addr + "&user_detail_addr=" + app.user_detail_addr
		            		+ "&deliv_name=" + app.deliv_name + "&deliv_phone1=" + app.deliv_phone1 + "&deliv_phone2=" + app.deliv_phone2 + "&deliv_phone3=" + app.deliv_phone3
		            		+ "&deliv_zip=" + app.deliv_zip + "&deliv_addr=" + app.deliv_addr + "&deliv_detail_addr=" + app.deliv_detail_addr;
                } else {
                    msg = '결제에 실패하였습니다.';
                    msg += '에러내용 : ' + rsp.error_msg;
                    //실패시 이동할 페이지
                    /* location.href='http://localhost:8081/Hanmall/productOrderFail.han?date=' + date + "&orderId=" + orderId + "&amount=" + amount + "&method=kakaoPay"
		            		+ "&user_num=" + app.user_num + "&user_name=" + app.user_name + "&user_phone1=" + app.user_phone1 + "&user_phone2=" + app.user_phone2 + "&user_phone3=" + app.user_phone3
		            		+ "&user_zip=" + app.user_zip + "&user_addr=" + app.user_addr + "&user_detail_addr=" + app.user_detail_addr
		            		+ "&deliv_name=" + app.deliv_name + "&deliv_phone1=" + app.deliv_phone1 + "&deliv_phone2=" + app.deliv_phone2 + "&deliv_phone3=" + app.deliv_phone3
		            		+ "&deliv_zip=" + app.deliv_zip + "&deliv_addr=" + app.deliv_addr + "&deliv_detail_addr=" + app.deliv_detail_addr; */
		            history.back();
                    alert(msg);
                }
            });
            
        });
	}
    
    function kakaoWaring() {
    	alert('카카오페이 테스트 버전의 QR결제 기능이 불안정합니다.\n\'종료된 요청입니다.\' 메세지가 출력되면 다른 결제방식으로 진행해주세요.');
    }
    
	let app = new Vue({
		el: '.container',
		data() {
		    return {
				user_num: <%=loginInfo.getUser_num()%>,
				user_name: '<%=loginInfo.getUser_name()%>',
				user_phone1: '<%=loginInfo.getUser_phone().substring(0, 3)%>',
				user_phone2: '<%=loginInfo.getUser_phone().substring(3, 7)%>',
				user_phone3: '<%=loginInfo.getUser_phone().substring(7, 11)%>',
				user_zip: '<%=loginInfo.getUser_zip()%>',
				user_addr: '<%=loginInfo.getUser_addr()%>',
				user_detail_addr: '<%=loginInfo.getUser_detail_addr()%>',
				
				deliv_name: '',
				deliv_phone1: '',
				deliv_phone2: '',
				deliv_phone3: '',
				deliv_zip: '',
				deliv_addr: '',
				deliv_detail_addr: '',
				
				isSendToMe : false,
				payMethods: '',
		    }
		},
		computed: {
			
		},
		methods: {
		    order() {
		    	if (this.user_name != ''
		    			&& this.user_phone1 != ''
		    			&& this.user_phone2 != ''
		    			&& this.user_phone3 != ''
		    			&& this.user_zip != ''
		    			&& this.user_addr != ''
		    			&& this.user_detail_addr != ''
		    			&& this.deliv_name != ''
		    			&& this.deliv_phone1 != ''
		    			&& this.deliv_phone2 != ''
		    			&& this.deliv_phone3 != ''
		   				&& this.deliv_zip != ''
		    			&& this.deliv_addr != ''
		    			&& this.deliv_detail_addr != ''
		    			&& this.payMethods != '') {
		    		if (this.payMethods == 'card') {
		    			window.tossPay();
		    		} else if (this.payMethods == 'kakaopay') {
		    			window.kakaopay();
		    		} else if (this.payMethods == 'phone') {
		    			window.phonePay();
		    		}
		    	} else {
		    		alert('입력하지 않은 정보가 있습니다');
		    	}
		    },
		    sendToMe() {
		    	this.isSendToMe = !this.isSendToMe;
		    	if (this.isSendToMe) {
		    		this.deliv_name = this.user_name;
					this.deliv_phone1 = this.user_phone1;
					this.deliv_phone2 = this.user_phone2;
					this.deliv_phone3 = this.user_phone3;
					this.deliv_zip = this.user_zip;
					this.deliv_addr = this.user_addr;
					this.deliv_detail_addr = this.user_detail_addr;
		    	} else {
		    		this.deliv_name = '';
					this.deliv_phone1 = '';
					this.deliv_phone2 = '';
					this.deliv_phone3 = '';
					this.deliv_zip = '';
					this.deliv_addr = '';
					this.deliv_detail_addr = '';
		    	}
		    },
		    numberCheck1() { 
		    	if (this.user_phone1.length != 0 && !(/^[0-9]+$/.test(this.user_phone1))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.user_phone1 = '';
		    	}
		    },
		    numberCheck2() { 
		    	if (this.user_phone2.length != 0 && !(/^[0-9]+$/.test(this.user_phone2))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.user_phone2 = '';
		    	}
		    },
		    numberCheck3() { 
		    	if (this.user_phone3.length != 0 && !(/^[0-9]+$/.test(this.user_phone3))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.user_phone3 = '';
		    	}
		    },
		    zipCheck1() { 
		    	if (this.user_zip.length != 0 && !(/^[0-9]+$/.test(this.user_zip))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.user_zip = '';
		    	}
		    },
		    numberCheck4() { 
		    	if (this.deliv_phone1.length != 0 && !(/^[0-9]+$/.test(this.deliv_phone1))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.deliv_phone1 = '';
		    	}
		    },
		    numberCheck5() { 
		    	if (this.deliv_phone2.length != 0 && !(/^[0-9]+$/.test(this.deliv_phone2))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.deliv_phone2 = '';
		    	}
		    },
		    numberCheck6() { 
		    	if (this.deliv_phone3.length != 0 && !(/^[0-9]+$/.test(this.deliv_phone3))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.deliv_phone3 = '';
		    	}
		    },
		    zipCheck2() { 
		    	if (this.deliv_zip.length != 0 && !(/^[0-9]+$/.test(this.deliv_zip))) {
		    		alert('숫자만 입력할 수 있습니다');
		    		this.deliv_zip = '';
		    	}
		    },
		 },
	});
	</script>
    <%@ include file="./Footer.jsp" %>
</body>
</html>