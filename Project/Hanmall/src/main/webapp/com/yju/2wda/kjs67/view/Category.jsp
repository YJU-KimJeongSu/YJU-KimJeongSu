<%@page import="java.net.InetAddress"%>
<%@page import="kjs67_hanmall.model.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kjs67_hanmall.model.CategoryDTO"%>
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
    <!-- Category.jsp는 들어오는 경로가 다양해서 상대경로 X -->
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/style.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Category.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
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
	<%
	request.setCharacterEncoding("utf-8");
	/* String category = request.getParameter("category");
	String order = request.getParameter("select");
	System.out.println("Category : category : " + category);
	System.out.println("Category : order : " + order); */
	
	CategoryDTO fullCategory = (CategoryDTO) request.getAttribute("fullCategory");
	if (fullCategory == null) fullCategory = new CategoryDTO();
	// System.out.println("Category : " + detailCategory.getCategory());
	// System.out.println("Category : " + detailCategory.getDetailCategory().get(1));
	
	/*
	* category = 현재 선택된 카테고리 대분류. all / office / fashion / living / craft / popular
	* deatailCategory = 현재 category의 모든 세부 카테고리 목록
	* order = 현재 정렬 순서. date / sell / price / rating / none
	* nowDCategory = 현재 선택된 세부 카테고리. 없으면 0
	*/
	String category = null;
	if (fullCategory != null) category = fullCategory.getCategory();
	if (category == null) category = "search";
	
	ArrayList<String> detailCategory = new ArrayList<String>();
	if (fullCategory != null) detailCategory = fullCategory.getDetailCategory();
	if (detailCategory == null) detailCategory = new ArrayList<String>();
	
	String order = request.getParameter("order");
	if (order == null) order = "none";
	String tempDCategory = request.getParameter("nowDCategory");
	if (tempDCategory == null) tempDCategory = "0";
	int nowDCategory = Integer.parseInt(tempDCategory);
	// System.out.println(order);
	ArrayList<ProductDTO> products = (ArrayList<ProductDTO>) request.getAttribute("products");
	
	// for (ProductDTO d : products) {
	//	System.out.println("Category : products : " + d.getProd_image0());
	// }
	%>
    <div class="container">
        <div class="detail-category font_NanumSquareRoundB">
            <!-- <button type="button" class="btn btn-secondary detail-category-btn font_NanumSquareRoundB">나중에</button>
            <button type="button" class="btn btn-secondary detail-category-btn font_NanumSquareRoundB">for문으로</button>
            <button type="button" class="btn btn-secondary detail-category-btn font_NanumSquareRoundB">하나하나</button>
            <button type="button" class="btn btn-secondary detail-category-btn font_NanumSquareRoundB">넣으면</button>
            <button type="button" class="btn btn-secondary detail-category-btn font_NanumSquareRoundB">되겠지</button> -->
            <%
            for (String s : detailCategory) {
            	%>
            	<button type="button"
            	class="btn btn-secondary detail-category-btn font_NanumSquareRoundB"
            	style="font-size: min(1.3vw, 20px);"
            	onclick="location.href='./category.han?category=<%=category%>&order=<%=order%>&nowDCategoryStr=<%=s%>'"><%=s%></button>
            	<%
            }
            %>
        </div>
        <div class="title">
            <p class="font_Jua"><%=category%> ></p>
            <%-- <select class="form-select select font_NanumSquareRoundB" onchange="location.href=(this.value)" style="width: 12vw; height: 3.2vw;">
            	<option disabled selected>정렬순서</option>
                <option value="./category.han?category=<%=category%>&order=date">등록일순</option>
                <option value="./category.han?category=<%=category%>&order=sell">판매량순</option>
                <option value="./category.han?category=<%=category%>&order=price">낮은 가격순</option>
                <option value="./category.han?category=<%=category%>&order=rating">별점순</option>
            </select> --%>
        </div>
        <div class="product-list">
            <div class="cards">
        <%
        int i = 0;
        for (ProductDTO d : products) {
        	if (i % 4 == 0) {
        	%>
        	</div> <!-- end of div class="product-list" -->
        </div> <!-- end of div class="cards" -->
        <div class="product-list">
            <div class="cards">
        	<%
        	}
        	%>
        	<a href="./productDetail.han?prod_num=<%=d.getProd_num()%>" class="text-deco">
                <div class="card">
                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/<%=d.getProd_image0()%>" class="card-img-top" alt="...">
                    <div class="card-body">
                        <p class="card-text font_NanumSquareRoundB"><%=d.getProd_name()%></p>
                        <p class="card-text font_NanumSquareRoundB" style="color: gray;"><%=d.getProd_price()%>원</p>
                    </div>
                </div>
            </a>
        	<%
        	
        	i++;
        	//System.out.println(i);
        }
        %>
        	</div> <!-- end of div class="product-list" -->
        </div> <!-- end of div class="cards" -->
        
        <%
        if (products.size() == 0) {
        	%>
        	<div class="blank-page font_Jua">
        		<p>상품이 준비중입니다.</p>
        	</div>
        	<%
        }
        %>
        
        <!----------------------------            디자인용 샘플 코드            ---------------------------->
        <%-- <div class="product-list">
            <div class="cards">
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">조금 기이이이이이이이이인 상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
            </div>
        </div>
        <div class="product-list">
            <div class="cards">
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
            </div>
        </div>
        <div class="product-list">
            <div class="cards">
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
                <a href="<%=Directory.PRODUCTDETAIL.getDirRedirect()%>" class="text-deco">
                	<div class="card">
                    	<img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
                    	<div class="card-body">
                        	<p class="card-text font_NanumSquareRoundB">상품명</p>
                        	<p class="card-text font_NanumSquareRoundB">1000원</p>
                    	</div>
                	</div>
                </a>
            </div>
        </div> --%>
        <!----------------------------            디자인용 샘플 코드            ---------------------------->
        <!-- <nav aria-label="Page navigation example" class="paging-bar font_Jua">
            <ul class="pagination">
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                  <span aria-hidden="true">&lt;&lt;</span>
                </a>
              </li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item"><a class="page-link" href="#">2</a></li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item"><a class="page-link" href="#">10</a></li>
              <li class="page-item"><a class="page-link" href="#">3000</a></li>
              <li class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                  <span aria-hidden="true">&gt;&gt;</span>
                </a>
              </li>
            </ul>
          </nav> -->
    </div>
    <%@ include file="./Footer.jsp" %>
</body>
</html>