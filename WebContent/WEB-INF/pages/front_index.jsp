<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Noto+Sans+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" integrity="sha256-HAaDW5o2+LelybUhfuk0Zh2Vdk8Y2W2UeKmbaXhalfA=" crossorigin="anonymous" />
    <style>
    	<%@include file="/WEB-INF/css/bootstrap.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
<title>Farmville 首頁</title>
 </head>
  <body>
    <!-- START -->
 <%@include file="/WEB-INF/pages/front_navbar.jsp" %>
    <!-- SHOWCASE SLIDER -->
    <section id="showcase">
      <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
          <div class="carousel-item carousel-image-1 active">
            <div class="container">
              <div class="dark-overlay">
              <div class="carousel-caption d-none d-sm-block text-right mb-5">
                <h1 class="display-3">新鮮直送</h1>
                <p class="lead">
                  讓您買得快速，吃得安心
                </p>
                <!-- 如果為空 才顯示 -->
				<jstl:if test="${empty cookie.loginSuccessCookie}">
					<a href=<jstl:url value="/directlogin"/> class="btn btn-success btn-lg btn-color-signup text-white">現在就登入</a>
				</jstl:if>
				<!-- 如果不為空 才顯示 -->
				<jstl:if test="${cookie.containsKey('loginSuccessCookie')}">
					<a href=<jstl:url value="/directservices"/> class="btn btn-success btn-lg btn-color-signup text-white">現在就購物</a>
				</jstl:if>
                
              </div>
              </div>
            </div>
          </div>

          <div class="carousel-item carousel-image-2">
            <div class="dark-overlay">
            <div class="container">
              <div class="carousel-caption d-none d-sm-block mb-5">
                <h1 class="display-3">嚴格篩檢</h1>
                <p class="lead">
                  蔬果送檢皆符合政府規範。保證看不到香菜
                </p>
              </div>
            </div>
          </div>
          </div>

          <div class="carousel-item carousel-image-3">
            <div class="dark-overlay">
            <div class="container">
              <div class="carousel-caption d-none d-sm-block text-right mb-5">
                <h1 class="display-3">購物新體驗</h1>
                <p class="lead">
                  直覺、方便、快速，退換貨不囉嗦
                </p>
              </div>
            </div>
          </div>
          </div>
        </div>

        <a href="#myCarousel" data-slide="prev" class="carousel-control-prev">
          <span class="carousel-control-prev-icon"></span>
        </a>

        <a href="#myCarousel" data-slide="next" class="carousel-control-next">
          <span class="carousel-control-next-icon"></span>
        </a>
      </div>
    </section>

    <!-- HOME ICON SECTION -->
    <section id="home-icons" class="py-5">
      <div class="container">
        <div class="row">
          <div class="col-md-4 mb-4 text-center">
            <i class="fas fa-user-shield fa-3x mb-2"> </i>
            <h3>為消費者把關</h3>
            <p>
              我們專注於維護販售蔬果的品質與安全，所有蔬果皆通過政府最高標準檢驗，讓消費者安心
            </p>
          </div>
          <div class="col-md-4 mb-4 text-center">
            <i class="fas fa-shopping-bag fa-3x mb-2"> </i>
            <h3>從農場到餐桌</h3>
            <p>
              讓您免出門，也能買到新鮮直送的蔬果
            </p>
          </div>
          <div class="col-md-4 mb-4 text-center">
            <i class="fab fa-alipay fa-3x mb-2"> </i>
            <h3>支付安全透明</h3>
            <p>
              消費者資訊高度保密。在網站看到的價格絕對是業界最優惠。本公司網站今年首年試營運，免運費！
            </p>
          </div>
        </div>
      </div>
    </section>

    <!-- HOME HEADING SECTION -->
    <section id="home-heading" class="p-5">
      <div class="dark-overlay">
        <div class="row">
          <div class="col">
            <div class="container pt-5">
              <h1>還在想什麼？</h1>
              <p class="d-none d-md-block">
                歡迎加入FarmVille線上買菜平台，絕對給您前所未有的買菜樂趣，買菜與玩開心農場一樣簡單
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- INFO SECTION -->
    <section id="info" class="py-3">
      <div class="container">
       <jstl:url var="theProductLink" value="/showSpecificProduct">
				<jstl:param name="productID" value="${product[0].productID}" />
	   </jstl:url>
        <div class="row">
          <div class="col-md-6 align-self-center">
            <h4>本月主打商品</h4>
            <h5> ${product[0].productName} </h5>
            <p>${product[0].productDescription}</p>
            <a href="${theProductLink}" class="btn btn-success btn-lg text-white">前往觀看</a>
          </div>
          <div class="col-md-6">
            <img src="${product[0].productImg }" alt="商品圖片" class="img-fluid" width="250px" />
          </div>
        </div>
      </div>
    </section>

    <!-- VIDEO PLAY -->
    <!-- 抓Youtube網址後的ID -->
    <section id="video-play">
      <div class="dark-overlay">
        <div class="row">
          <div class="col">
            <div class="container p-5">
              <a
                href="#"
                class="video"
                data-video="https://www.youtube.com/embed/Icg1cjQED8I"
                data-toggle="modal"
                data-target="#videoModal"
              >
                <i class="fas fa-play fa-3x"></i>
              </a>
              <h1>我們在做什麼？</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- PHOTO PRODUCT GALLERY -->
    <!-- 這部分要額外CDN -->
    <section id="gallery" class="py-5">
      <div class="container">
        <h1 class="text-center">促銷商品</h1>
        <p class="text-center">點按商品可以看更多商品資訊</p>
        <div class="row mb-4">
        
        <jstl:forEach begin="4" end="9" var="product" items="${product}">
          <jstl:url var="theProductLink" value="/showSpecificProduct">
				<jstl:param name="productID" value="${product.productID}" />
		  </jstl:url>
          <div class="col-md-4">
            <a href="${theProductLink}" data-height="560"
              data-width="560">
              <img src="${product.productImg}" alt="" class="img-fluid">
            </a>
          </div>
       </jstl:forEach>
        
        </div>
      </div>
    </section>

    <!-- NEWSLETTER -->
    <section id="newsletter" class="text-center p-5 bg-dark text-white">
      <div class="container">
        <div class="row">
          <div class="col">
            <h1>訂閱我們的電子報</h1>
            <p>即時收到本公司的最新通知，有任何食品促銷會在第一時間通知您，您也可以隨時取消訂閱</p>
				<form action="<jstl:url value="/joinNewsletter" />" class="form-inline justify-content-center" method="POST">
					<input type="email" name="inputEmail" class="form-control mb-2 mr-2" placeholder="輸入電子信箱" value="${errors.messageError}">
					<input type="submit" class="btn btn-success text-white mb-2" value="送出"/>
				</form>
          </div>
        </div>
      </div>
    </section>

    <!-- FOOTER -->
    <footer id="main-footer" class="text-center p-4 ">
      <div class="container">
        <div class="row">
          <div class="col">
            <p>版權所有 &copy;
              <span id="year"> FarmVille</span>
            </p>
          </div>
        </div>
      </div>
    </footer>

    <!-- VIDEO MODAL -->
    <div class="modal fade" id="videoModal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <button class="close" data-dismiss="modal">
              <span>&times;</span>
            </button>
            <iframe
              src=""
              frameborder="0"
              height="350"
              width="100%"
              allowfullscreen
            ></iframe>
          </div>
        </div>
      </div>
    </div>

    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
      integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
      crossorigin="anonymous"
    ></script>
    <script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
      integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
      crossorigin="anonymous"
    ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.min.js" integrity="sha256-Y1rRlwTzT5K5hhCBfAFWABD4cU13QGuRN6P5apfWzVs=" crossorigin="anonymous"></script>
    <script>
      // Get the current year for copyright
      $("#year").text(new Date().getFullYear());

      // Configure Slider
      $(".carousel").carousel({
        interval: 6000,
        pause: "hover"
      });
      // LightBox Init
      $(document).on('click', '[data-toggle="lightbox"]', function(event) {
                event.preventDefault();
                $(this).ekkoLightbox();
            });

      // Video Play
      $(function() {
        // Auto play modal video
        $(".video").click(function() {
          var theModal = $(this).data("target"),
            videoSRC = $(this).attr("data-video"),
            videoSRCauto =
              videoSRC +
              "?modestbranding=1&rel=0&controls=0&showinfo=0&html5=1&autoplay=1";
          $(theModal + " iframe").attr("src", videoSRCauto);
          $(theModal + " button.close").click(function() {
            $(theModal + " iframe").attr("src", videoSRC);
          });
        });
      });
    </script>
  </body>
</html>
