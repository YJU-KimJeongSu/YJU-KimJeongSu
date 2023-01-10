<%@page import="kjs67_hanmall.etc.Directory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
        crossorigin="anonymous"></script>
    <!-- <link rel="stylesheet" href="../src/css/style.css">
    <link rel="stylesheet" href="../src/css/Header.css">  -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
    <title>Document</title>
</head>
<body>
	<%
	String alert = null;
	alert = (String) session.getAttribute("alert");
	if (alert != null && !alert.equals("") && !alert.equals("null")) {
		%>
		<script type="text/javascript">
			alert("<%=alert%>");
		</script>
		<%
		alert = null;
		session.setAttribute("alert", "");
	}
	%>
    <div class="header-container font_NanumGothicBold">
        <div class="header-innercontainer">
            <div class="header-left">
                <button onclick="location.href='<%=Directory.INDEX.getDirFull()%>'"><img src="/Hanmall/com/yju/2wda/kjs67/src/image/logo2.png"></button>
            </div>
            <div class="header-mid">
                <form action="./productSearch.han" method="post" id="searchform">
		            <div class="header-search-box">
		                <input type="text" class="header-search-txt font_NanumSquareRoundB" name="searchKeyword" placeholder="검색어를 입력해주세요">
		                <a class="header-search-btn" onclick="document.getElementById('searchform').submit();">
		                    <i class="fas fa-search"></i>
		                </a>
		            </div>
            	</form>
            </div>
            <div class="header-right">
                <a href="./logout.han">로그아웃</a>
                <a href="<%=Directory.PASSWORDCHECK.getDirFull()%>">마이페이지</a>
                <a href="./cart.han">장바구니</a>
            </div>
        </div>
    </div>
    <hr>
    <div class="header-navContainer font_NanumGothicBold">
        <a href="./category.han?category=all">전체 상품</a> 
        <a href="./category.han?category=popular">인기 상품</a>
        <a href="./category.han?category=office">문구/사무</a>
        <a href="./category.han?category=fashion">패션잡화</a>
        <a href="./category.han?category=living">생활용품</a>
        <a href="./category.han?category=craft">공예품</a>
    </div>
    <hr>
</body>
</html>