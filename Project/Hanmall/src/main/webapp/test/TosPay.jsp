<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://js.tosspayments.com/v1/payment"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
</head>
<body>
	<script>
    	var clientKey = 'test_ck_D5GePWvyJnrK0W0k6q8gLzN97Eoq'
    	var tossPayments = TossPayments(clientKey) // 클라이언트 키로 초기화하기
    	
    	let app = new Vue({
    		el: '.container',
    		data() {
    		    return {
    		    	price: 10000,
    		    }
    		},
    		computed: {
    			
    		},
    		methods: {
    		    
    		 },
    	});
    	
    	tossPayments.requestPayment('가상계좌', { // 결제 수단 파라미터
    		  // 결제 정보 파라미터
    		amount: app.price,
    		orderId: 'qE_Xi-kR9BgZdWeqw8TWt',
    		orderName: '토스 티셔츠 외 2건',
    		customerName: '박토스',
    		successUrl: 'http://localhost:8081/Hanmall/logout.han',
    		failUrl: 'http://localhost:8081/fail',
    		validHours: 24,
    		cashReceipt: {
    		type: '소득공제',
    		},
    	})
	</script>
	<div class="container">
		test page
	</div>
</body>
</html>