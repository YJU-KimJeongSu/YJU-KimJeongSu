<%@page import="kjs67_hanmall.model.LoginDTO"%>
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
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/style.css">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/index.css">
    <!-- css 캐시로 이용해서 변경사항 적용 안될때 ?after 추가 -->
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Header.css?after">
    <link rel="stylesheet" href="/Hanmall/com/yju/2wda/kjs67/src/css/Footer.css?after">
    <title>Document</title>
</head>

<body>
	<%
	// session.setAttribute("productOrderList", null);
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
		<%@ include file="/com/yju/2wda/kjs67/view/Header_login.jsp" %>
		<%
	} else if (user_class == 2) { // 판매자
		%>
		<%@ include file="/com/yju/2wda/kjs67/view/Header_seller.jsp" %>
		<%
	} else if (user_class == 9) { // 관리자
		%>
		<%@ include file="/com/yju/2wda/kjs67/view/Header_admin.jsp" %>
		<%
	} else { // 로그아웃
		%>
		<%@ include file="/com/yju/2wda/kjs67/view/Header_logout.jsp" %>
		<%
	}
	%>
    <div class="container">
        <!-- <div id="eventBanner">
            <div id="slideWrap">
                <ul id="slider">
                    <li><a href="#"><img src="./com/yju/2wda/kjs67/src/image/event_banner_1.png" alt="슬라이드1"></a></li>
                    <li><a href="#"><img src="./com/yju/2wda/kjs67/src/image/event_banner_2.png" alt="슬라이드2"></a></li>
                    <li><a href="#"><img src="./com/yju/2wda/kjs67/src/image/event_banner_3.png" alt="슬라이드3"></a></li>
                    <li><a href="#"><img src="./com/yju/2wda/kjs67/src/image/event_banner_4.png" alt="슬라이드4"></a></li>
                </ul>
              </div>
        </div> -->
        <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active" data-bs-interval="5000">
                    <a href="#">
                        <img src="/Hanmall/com/yju/2wda/kjs67/src/image/event_banner_1.png" class="d-block w-100" alt="...">
                    </a>
                </div>
                <div class="carousel-item" data-bs-interval="5000">
                    <a href="#">
                        <img src="/Hanmall/com/yju/2wda/kjs67/src/image/event_banner_2.png" class="d-block w-100" alt="...">
                    </a>
                </div>
                <div class="carousel-item" data-bs-interval="5000">
                    <a href="#">
                        <img src="/Hanmall/com/yju/2wda/kjs67/src/image/event_banner_3.png" class="d-block w-100" alt="...">
                    </a>
                </div>
                <div class="carousel-item" data-bs-interval="5000">
                    <a href="#">
                        <img src="/Hanmall/com/yju/2wda/kjs67/src/image/event_banner_4.png" class="d-block w-100" alt="...">
                    </a>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
        <br>
        <hr>
        <div class="categorys">
            <%-- <span class="title"><a href="<%=Directory.CATEGORY.getDirRedirect()%>?category=popular" class="font_Jua">인기 상품 ></a></span> --%>
            <span class="title"><a href="./category.han?category=popular" class="font_Jua">인기 상품 ></a></span>
            <div class="cards">
                <a href="./productDetail.han?prod_num=5" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/앎.참.얼.빛.삶 반양장 무지 노트3.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">앎.참.얼.빛.삶 반양장 무지 노트</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">7000원</p>
	                    </div>
	                </div>
	            </a>
                <a href="./productDetail.han?prod_num=28" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/자개 스티커(신라금관 백색).jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">자개 스티커(신라금관 백색)</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">18000원</p>
	                    </div>
	                </div>
	            </a>
                <a href="./productDetail.han?prod_num=34" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/조선시대 실경산수화 손수건.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">조선시대 실경산수화 손수건</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">15000원</p>
	                    </div>
	                </div>
	            </a>
                <a href="./productDetail.han?prod_num=37" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/천마도 양말.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">천마도 양말</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">2500원</p>
	                    </div>
	                </div>
	            </a>
            </div>
        </div>
        <hr>
        <div class="categorys">
            <span class="title"><a href="./category.han?category=office" class="font_Jua">문구/사무 ></a></span>
            <div class="cards">
                <a href="./productDetail.han?prod_num=5" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/앎.참.얼.빛.삶 반양장 무지 노트3.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">앎.참.얼.빛.삶 반양장 무지 노트</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">7000원</p>
	                    </div>
	                </div>
	            </a>
	            <a href="./productDetail.han?prod_num=28" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/자개 스티커(신라금관 백색).jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">자개 스티커(신라금관 백색)</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">18000원</p>
	                    </div>
	                </div>
	            </a>
	            <a href="./productDetail.han?prod_num=24" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/민화마우스패드.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">민화마우스패드</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">20000원</p>
	                    </div>
	                </div>
	            </a>
	            <a href="./productDetail.han?prod_num=22" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/나전칠기 마그넷.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">나전칠기 마그넷</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">10000원</p>
	                    </div>
	                </div>
	            </a>
            </div>
        </div>
        <hr>
        <div class="categorys">
            <span class="title"><a href="./category.han?category=fashion" class="font_Jua">패션잡화 ></a></span>
            <div class="cards">
                <a href="./productDetail.han?prod_num=34" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/조선시대 실경산수화 손수건.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">조선시대 실경산수화 손수건</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">15000원</p>
	                    </div>
	                </div>
	            </a>
                <a href="./productDetail.han?prod_num=37" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/천마도 양말.jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">천마도 양말</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">2500원</p>
	                    </div>
	                </div>
	            </a>
	            <a href="./productDetail.han?prod_num=33" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/사리외호 손수건(남색).jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">사리외호 손수건(남색)</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">15000원</p>
	                    </div>
	                </div>
	            </a>
                <a href="./productDetail.han?prod_num=41" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product/일월오봉도 천가방(무지개).jpg" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">일월오봉도 천가방(무지개)</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">25000원</p>
	                    </div>
	                </div>
	            </a>
            </div>
        </div>
        <hr>
        <div class="categorys">
            <span class="title"><a href="./category.han?category=living" class="font_Jua">생활용품 ></a></span>
            <div class="cards">
                <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
                <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
                <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
                <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
            </div>
        </div>
        <hr>
        <div class="categorys">
            <span class="title"><a href="./category.han?category=craft" class="font_Jua">공예품 ></a></span>
            <div class="cards">
                <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
                <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
	            <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
	            <a href="#" class="text-deco">
	                <div class="card">
	                    <img src="/Hanmall/com/yju/2wda/kjs67/src/image/product.png" class="card-img-top" alt="...">
	                    <div class="card-body">
	                        <p class="card-text font_NanumSquareRoundB">상품 준비중</p>
	                        <p class="card-text font_NanumSquareRoundB" style="color: gray;">0원</p>
	                    </div>
	                </div>
	            </a>
            </div>
        </div>
    </div>
    <%@ include file="./com/yju/2wda/kjs67/view/Footer.jsp" %>
    <!-- <script>
        //자동 스크롤 애니메이션
        var ul = document.querySelector('#slider')
        var slideNumber = document.querySelector('#slider').childElementCount
        var li = document.querySelector('#slider>li')

        var firstItemClone = ul.firstElementChild.cloneNode(true);
        ul.appendChild(firstItemClone);
        ul.style.width = (slideNumber + 1) * 1200 + 'px'

        function move() {
            var i = 0;

            setInterval(function () {
                ul.style.transition = '0.2s';
                ul.style.transform = 'translate3d(-' + 1200 * (i + 1) + 'px, 0px, 0px)';

                i++;

                if (i == slideNumber) {
                    setTimeout(function () {
                        ul.style.transition = '0s';
                        ul.style.transform = "translate3d(0px, 0px, 0px)";
                    }, 201)
                    i = 0;
                }
            }, 3000);
        }

        document.addEventListener("DOMContentLoaded", function () {
            move();
        });

    </script> -->
</body>
</html>