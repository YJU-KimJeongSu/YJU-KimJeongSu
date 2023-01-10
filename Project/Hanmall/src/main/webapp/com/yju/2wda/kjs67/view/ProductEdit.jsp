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
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/ProductReg.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
	<script src="https://cdn.jsdelivr.net/npm/vue@2/dist/vue.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <script src="/Hanmall/com/yju/2wda/kjs67/src/etc/smarteditor2-2.9.1/workspace/js/service/HuskyEZCreator.js"></script>
    <title>Document</title>
</head>
<body>
    <%
	LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
	// if (loginInfo != null) System.out.println("index : now user class : " + loginInfo.getUser_class());
	
	ProductDTO product = (ProductDTO) request.getAttribute("product");
	// System.out.println("ProductEdit.jsp : product : " + product);
	
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
	<form action="./productEdit.han" method="post" id="productform">
	    <div class="container">
	        <div class="product-table">
	            <h2 class="font_NanumSquareRoundEB">상품 정보 수정</h2>
	            <label for="productName" class="font_NanumSquareRoundEB">상품명 <span class="red">* 　　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="productName" name="productName" value="<%=product.getProd_name()%>" required>
	            <br>
	            <label for="sellerName" class="font_NanumSquareRoundEB">판매자명 <span class="red">* 　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="sellerName" name="sellerName" value="<%=product.getProd_seller()%>"  required>
	            <br>
	            <label for="price" class="font_NanumSquareRoundEB">판매가 <span class="red">* 　　</span></label>
	            <input type="number" class="inputbox font_NanumSquareRoundB" id="price" name="price" value="<%=product.getProd_price()%>"  required min="1">
	            <br>
	            <label for="productCount" class="font_NanumSquareRoundEB">재고 <span class="red">* 　　　</span></label>
	            <input type="number" class="inputbox font_NanumSquareRoundB" id="productCount" name="productCount" value="<%=product.getProd_count()%>"  required min="1">
	            <br>
	            <div class="summary">
	                <label for="productSummary" class="font_NanumSquareRoundEB">상품 요약 <span class="red">* 　</span></label>
	                <!-- <input type="text" class="longbox font_NanumSquareRoundB" id="productSummary"> -->
	                <textarea name="productSummary" id="productSummary" cols="30" rows="3" class="longbox font_NanumSquareRoundB" required><%=product.getProd_summary()%></textarea>
	                <!-- 나중에 Vue나 js나 jQuery로 글자수 제한 넣어야함 -->
	            </div>
	            <br>
	            <div style="display: flex; flex-direction: row; align-items: center; font-size: min(2vw, 20px);">
		            <label for="editorTxt" class="font_NanumSquareRoundEB">상세설명 <span class="red">* 　　</span></label>
				    <div id="smarteditor"  style="background-color: white; width: 572px;">
				        <textarea name="editorTxt" id="editorTxt" 
				        		rows="10" cols="15" 
				                placeholder="내용을 입력해주세요"
				                style="width: 500px;"><%=product.getProd_content()%></textarea>
				    </div>
	            </div>
	            <br>
	            <p class="font_NanumSquareRoundEB" style="text-align: center; color: red;">상품 이미지, 카테고리는 수정할 수 없습니다</p>
	        </div>
	        <div class="btns font_Jua">
	        	<input type="hidden" name="prod_num" value="<%=product.getProd_num()%>">
	            <button type="button" class="btn btn-primary font_Jua" onclick="submitPost()">수정하기</button>
	        </div>
	    </div>
	</form>
	<script>
	let app = new Vue({
		el: '.container',
		data() {
		    return {
				selectedCategory: '',
		    }
		},
		computed: {
			
		},
		methods: {
		    
		 },
	});
	
	// 네이버 스마트 에디터
	let oEditors = []

    smartEditor = function() {
      	nhn.husky.EZCreator.createInIFrame({
        	oAppRef: oEditors,
        	elPlaceHolder: "editorTxt",
        	sSkinURI: "/Hanmall/com/yju/2wda/kjs67/src/etc/smarteditor2-2.9.1/workspace/SmartEditor2Skin.html",
        	fCreator: "createSEditor2"
      	})
    }

    $(document).ready(function() {
      	smartEditor()
    })
    
    // 네이버 스마트 에디터 - 내용 확인하고 제출하기
    submitPost = function() {
	  	oEditors.getById["editorTxt"].exec("UPDATE_CONTENTS_FIELD", [])
	  	let content = document.getElementById("editorTxt").value
	
	  	if(content == '') {
	    	alert("상세설명을 입력해주세요.")
	  	} else if (content.length > 3000) {
	  		alert("상세설명 길이가 너무 깁니다.")
	  	} else {
	  		document.getElementById('productform').submit();
	  	}
    }
	</script>
    <%@ include file="./Footer.jsp" %>
</body>
</html>