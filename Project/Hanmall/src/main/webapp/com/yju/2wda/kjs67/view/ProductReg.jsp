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
    <link rel="stylesheet" href="../src/css/ProductReg.css">
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
	<form action="./productReg.han" method="post" enctype="multipart/form-data" id="productform">
	    <div class="container">
	        <div class="product-table">
	            <h2 class="font_NanumSquareRoundEB">상품 등록</h2>
	            <label for="productName" class="font_NanumSquareRoundEB">상품명 <span class="red">* 　　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="productName" name="productName" required>
	            <br>
	            <label for="sellerName" class="font_NanumSquareRoundEB">판매자명 <span class="red">* 　</span></label>
	            <input type="text" class="inputbox font_NanumSquareRoundB" id="sellerName" name="sellerName" required>
	            <br>
	            <label for="productCategory" class="font_NanumSquareRoundEB">카테고리 <span class="red">* 　</span></label>
	            <!-- <input type="text" class="inputbox font_NanumSquareRoundB" id="productCategory"> -->
	            <select name="category1" id="productCategory" class="select-box font_NanumSquareRoundB" v-model="selectedCategory" required>
	                <option value="" disabled selected>카테고리 선택</option>
	                <option value="office">문구/사무</option>
	                <option value="fashion">패션잡화</option>
	                <option value="living">생활용품</option>
	                <option value="craft">공예품</option>
	            </select>
	            <span class="font_NanumSquareRoundEB">&nbsp;&nbsp;></span>
	            <!-- <select name="category2" class="select-box font_NanumSquareRoundB">
	                <option value="" disabled selected>세부 카테고리</option>
	                <option value="1">이건</option>
	                <option value="2">앞에 선택값 따라</option>
	                <option value="3">Vue로</option>
	                <option value="4">맞춰 넣기</option>
	                <option value="5">맞춰 넣기</option>
	            </select> -->
	            <select required name="category2" class="select-box font_NanumSquareRoundB" v-if="selectedCategory == 'office'">
	                <option value="1">노트/수첩</option>
	                <option value="2">필기구</option>
	                <option value="3">카드/엽서</option>
	                <option value="4">데스크용품</option>
	                <option value="5">기타</option>
	            </select>
	            <select required name="category2" class="select-box font_NanumSquareRoundB" v-else-if="selectedCategory == 'fashion'">
	                <option value="1">스카프/손수건</option>
	                <option value="2">의류</option>
	                <option value="3">가방/지갑</option>
	                <option value="4">악세사리</option>
	                <option value="5">기타</option>
	            </select>
	            <select required name="category2" class="select-box font_NanumSquareRoundB" v-else-if="selectedCategory == 'living'">
	                <option value="1">인테리어 소품</option>
	                <option value="2">주방/식기</option>
	                <option value="3">생활잡화</option>
	                <option value="4">기타</option>
	            </select>
	            <select required name="category2" class="select-box font_NanumSquareRoundB" v-else-if="selectedCategory == 'craft'">
	                <option value="1">도자기</option>
	                <option value="2">수공예품</option>
	                <option value="3">기타</option>
	            </select>
	            <select class="select-box font_NanumSquareRoundB" v-else>
	                <option disabled selected>세부 카테고리</option>
	            </select>
	            <br>
	            <label for="price" class="font_NanumSquareRoundEB">판매가 <span class="red">* 　　</span></label>
	            <input type="number" class="inputbox font_NanumSquareRoundB" id="price" name="price" required min="1">
	            <br>
	            <label for="productCount" class="font_NanumSquareRoundEB">재고 <span class="red">* 　　　</span></label>
	            <input type="number" class="inputbox font_NanumSquareRoundB" id="productCount" name="productCount" required min="1">
	            <br>
	            <div class="summary">
	                <label for="productSummary" class="font_NanumSquareRoundEB">상품 요약 <span class="red">* 　</span></label>
	                <!-- <input type="text" class="longbox font_NanumSquareRoundB" id="productSummary"> -->
	                <textarea name="productSummary" id="productSummary" cols="30" rows="3" class="longbox font_NanumSquareRoundB" required @keyup="checkLength()" v-model="area"></textarea>
	                <!-- 나중에 Vue나 js나 jQuery로 글자수 제한 넣어야함 -->
	            </div>
	            <br>
	            <div style="display: flex; flex-direction: row; align-items: center; font-size: min(2vw, 20px);">
		            <label for="editorTxt" class="font_NanumSquareRoundEB">상세설명 <span class="red">* 　　</span></label>
				    <div id="smarteditor"  style="background-color: white; width: 572px;">
				        <textarea name="editorTxt" id="editorTxt" 
				        		rows="10" cols="15" 
				                placeholder="내용을 입력해주세요"
				                style="width: 500px;"></textarea>
				    </div>
	            </div>
			    <br>
	            <div class="inline">
	                <label for="inputGroupFile00" class="font_NanumSquareRoundEB">대표 이미지 <span class="red">*&nbsp;&nbsp;　</span></label>
	                <div class="input-group mb-3">
	                    <input type="file" class="form-control font_Jua file-upload" id="inputGroupFile00" accept="image/*" name="inputGroupFile00" required>
	                </div>
	            </div>
	            <div class="inline">
	                <label for="inputGroupFile01" class="font_NanumSquareRoundEB">보조 이미지1 <span class="red">&nbsp;　</span></label>
	                <div class="input-group mb-3">
	                    <input type="file" class="form-control font_Jua file-upload" id="inputGroupFile01" accept="image/*" name="inputGroupFile01">
	                </div>
	            </div>
	            <div class="inline">
	                <label for="inputGroupFile02" class="font_NanumSquareRoundEB">보조 이미지2 <span class="red">&nbsp;　</span></label>
	                <div class="input-group mb-3">
	                    <input type="file" class="form-control font_Jua file-upload" id="inputGroupFile02" accept="image/*" name="inputGroupFile02">
	                </div>
	            </div>
	            <div class="inline">
	                <label for="inputGroupFile03" class="font_NanumSquareRoundEB">보조 이미지3 <span class="red">&nbsp;　</span></label>
	                <div class="input-group mb-3">
	                    <input type="file" class="form-control font_Jua file-upload" id="inputGroupFile03" accept="image/*" name="inputGroupFile03">
	                </div>
	            </div>
	            <div class="inline">
	                <label for="inputGroupFile04" class="font_NanumSquareRoundEB">보조 이미지4 <span class="red">&nbsp;　</span></label>
	                <div class="input-group mb-3">
	                    <input type="file" class="form-control font_Jua file-upload" id="inputGroupFile04" accept="image/*" name="inputGroupFile04">
	                </div>
	            </div>
	            <div class="inline">
	                <label for="inputGroupFile05" class="font_NanumSquareRoundEB">보조 이미지5 <span class="red">&nbsp;　</span></label>
	                <div class="input-group mb-3">
	                    <input type="file" class="form-control font_Jua file-upload" id="inputGroupFile05" accept="image/*" name="inputGroupFile05">
	                </div>
	            </div>
	        </div>
	        <div class="btns font_Jua">
	        <input type="hidden" name="user_num" value="<%=loginInfo.getUser_num()%>">
	            <!-- <button type="submit" class="btn btn-primary font_Jua">등록하기</button> -->
	            <button type="button" class="btn btn-primary font_Jua" onclick="submitPost()">등록하기</button>
	        </div>
	    </div>
	</form>
	<script>
	let app = new Vue({
		el: '.container',
		data() {
		    return {
				selectedCategory: '',
				area: '',
		    }
		},
		computed: {
			
		},
		methods: {
			checkLength() {
				if (this.area.length > 100) {
					alert('상품 요약은 최대 100글자까지 가능합니다');
					this.area = this.area.slice(0, 100);
				}
			},
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