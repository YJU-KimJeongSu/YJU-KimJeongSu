<%@page import="kjs67_hanmall.model.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
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
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/ProductManage.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
    <title>Document</title>
</head>
<body>
    <%
	LoginDTO loginInfo = (LoginDTO) session.getAttribute("userInfo");
	// if (loginInfo != null) System.out.println("index : now user class : " + loginInfo.getUser_class());
	
	ArrayList<ProductDTO> userProductList = (ArrayList<ProductDTO>) request.getAttribute("userProductList");
//	for (ProductDTO p : userProductList) {
//		System.out.println("ProductManage" + p);
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
    <div class="container">
	    <div class="font_NanumSquareRoundB" style="align-self: flex-end;">
		    <button type="submit" class="btn btn-primary" style="margin: 1vw; width: 100px;" onclick="location.href='<%=Directory.PRODUCTREG.getDirFull()%>'">상품 등록</button>
	    </div>
	    <div style="background-color: whitesmoke;">
	        <table class="table table-striped" style="text-align: center;">
	            <thead class="font_NanumSquareRoundB">
	                <tr>
	                    <th scope="col">상품코드</th>
	                    <th scope="col">이미지</th>
	                    <th scope="col">재고</th>
	                    <th scope="col">상품명</th>
	                    <th scope="col">판매가</th>
	                    <th scope="col">판매수량</th>
	                    <th scope="col">수정</th>
	                    <th scope="col">삭제</th>
	                </tr>
	            </thead>
	            <tbody class="font_NanumSquareRoundR">
	            <%
	            if (userProductList.size() > 0) {
		            for (ProductDTO p : userProductList) {
		            	%>
		            	<tr>
		                    <th scope="row"><%=p.getProd_num()%></th>
		                    <td><img src="<%=Directory.PRODUCTTHUMB.getDirFull() + p.getProd_thumb_80()%>" alt=""></td>
		                    <td><%=p.getProd_count()%></td>
		                    <td><%=p.getProd_name()%></td>
		                    <td><%=p.getProd_price()%></td>
		                    <td><%=p.getProd_sellcount()%></td>
		                    <td>
		                    	<form action="./productEditPage.han" method="post">
		                    		<input type="hidden" name="prod_num" value="<%=p.getProd_num()%>">
		                    		<button type="submit" class="btn btn-outline-secondary tablebtn" style="padding: 0.1vw 0.2vw 0.1vw 0.2vw;">수정</button>
		                    	</form>
		                    </td>
		                    <td>
		                    	<form action="./productDelete.han" method="post">
		                    		<input type="hidden" name="prod_num" value="<%=p.getProd_num()%>">
		                    		<button type="submit" class="btn btn-outline-secondary tablebtn" style="padding: 0.1vw 0.2vw 0.1vw 0.2vw;">삭제</button>
		                    	</form>
		                    </td>
		                </tr>
		            	<%
		            }
	            } else {
	            	%>
	            	<tr>
	            		<td colspan="8" class="font_Jua" style="font-size: 3vw;">등록된 상품이 없습니다</td>
	            	</tr>
	            	<%
	            }
	            %>
	                <!-- <tr>
	                    <th scope="row">1</th>
	                    <td><img src="../src/image/80product.png" alt=""></td>
	                    <td>Otto</td>
	                    <td>@mdo</td>
	                    <td>100000</td>
	                    <td>판매중</td>
	                    <td>100</td>
	                    <td><button type="button" class="btn btn-outline-secondary">수정</button></td>
	                    <td><button type="button" class="btn btn-outline-secondary">삭제</button></td>
	                </tr>
	                <tr>
	                    <th scope="row">2</th>
	                    <td><img src="../src/image/80product.png" alt=""></td>
	                    <td>Otto</td>
	                    <td>@mdo</td>
	                    <td>1000</td>
	                    <td>판매중</td>
	                    <td>100</td>
	                    <td><button type="button" class="btn btn-outline-secondary">수정</button></td>
	                    <td><button type="button" class="btn btn-outline-secondary">삭제</button></td>
	                </tr>
	                <tr>
	                    <th scope="row">3</th>
	                    <td><img src="../src/image/80product.png" alt=""></td>
	                    <td>Otto</td>
	                    <td>@mdo</td>
	                    <td>10000</td>
	                    <td>판매중</td>
	                    <td>100</td>
	                    <td><button type="button" class="btn btn-outline-secondary">수정</button></td>
	                    <td><button type="button" class="btn btn-outline-secondary">삭제</button></td>
	                </tr> -->
	            </tbody>
	        </table>
	    </div>
    </div>
    <%@ include file="./Footer.jsp" %>
</body>
</html>