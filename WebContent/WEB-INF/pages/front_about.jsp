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
    <link rel="stylesheet" href="css/bootstrap.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" integrity="sha256-HAaDW5o2+LelybUhfuk0Zh2Vdk8Y2W2UeKmbaXhalfA=" crossorigin="anonymous" />
    <style>
    	<%@include file="/WEB-INF/css/bootstrap.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>

  <title>關於我們</title>
  </head>
  <body>
 <!-- START -->
 
<!-- NAVBAR -->
<%@include file="/WEB-INF/pages/front_navbar.jsp" %>

   <!-- PAGE HEADER -->
    <header id="page-header">
      <div class="dark-overlay-title"> 
        <div class="container">
            <div class="row">
                <div class="col-md-6 m-auto text-center">
                    <h1>關於我們</h1>
                </div>
            </div>
          </div>  
        </div>
    </header>

    <!-- ABOUT SECTION -->
    <section id="about" class="py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h1>我們做些什麼</h1>
                    <p>我們是一群來自資策會的合作夥伴，對於線上健康蔬果的提供有著熱情與專業。隨著科技不斷進步，人們越來越倚靠線上購物取得生活所需，幾乎達到不出門就可以滿足食衣住行的需要。現在的消費者對於食品新鮮度以及品質安全越來越重視，蔬果類的線上購物好選擇卻仍然不敷所需，因此我們成立了這個以消費者需求為主的線上蔬果網站。</p>
                    <p>為了讓消費者們安心選購，我們堅持在蔬果來源及檢驗各個項目上，皆符合政府規範；我們秉持蔬果符合從農場到餐桌的管理，讓客戶吃得新鮮，吃得安心。歡迎透過<a href="directcontact.html">聯繫我們</a>詢問，也可以透過追蹤我們的<a href=<jstl:url value="/directIG"/>>Instagram</a>透過<a href=<jstl:url value="/directIG"/>>Instagram</a>私訊聯絡到我們，如果想買紅蘿蔔卻不小心買到白蘿蔔，即使拆封了，都歡迎您退換貨喔！</p>
                    <p>目前武漢肺炎盛行，請大家為了自身的健康、少去擁擠的市場，多透過本公司網站選購健康蔬果喔❤</p>
                </div>
                <div class="col-md-6">
                    <img src="https://source.unsplash.com/random/700x700/?food" alt="" class="img-fluid rounded-circle d-none d-md-block about-img">
                </div>
            </div>
        </div>
    </section>

    <!-- ICON BOXES -->
    <section id="icon-boxes" class="p-5">
        <div class="container">
            <div class="row mb-4">
                <div class="col-md-4">
                    <div class="card bg-success text-white text-center card-height">
                        <div class="card-body">
                            <i class="fas fa-shipping-fast fa-3x"></i>
                            <h3>6hrs到貨</h3>
                            講究食品新鮮度，蔬果在物流過程中絕對不停留超過24小時，就能讓這些蔬果到達您家大門，準備進駐您的冰箱。
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card bg-dark text-white text-center card-height">
                        <div class="card-body">
                            <i class="fas fa-exchange-alt fa-3x"></i>
                            <h3>無條件退換貨</h3>
                            秉持新鮮至上及消費者健康第一，如果您覺得紅蘿蔔太小，或紅蘿蔔買成了南瓜，都歡迎您向我們退換貨。
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card bg-success text-white text-center card-height">
                        <div class="card-body">
                            <i class="fas fa-user-check fa-3x"></i>
                            <h3>24hrs客服服務</h3>
                            透過聯絡我們，我們將立即與您聯繫，竭誠為您解決購物上的疑問或是售後退換貨。
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col-md-4">
                    <div class="card bg-dark text-white text-center card-height">
                        <div class="card-body">
                            <i class="fab fa-fedex fa-3x"></i>
                            <h3>與Fedex合作</h3>
                            When there is no tomorrow...
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card bg-success text-white text-center card-height">
                        <div class="card-body">
                            <i class="fab fa-usps fa-3x"></i>
                            <h3>與Usps合作</h3>
                            絕對讓您不只有一種快速到府的選擇...
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card bg-dark text-white text-center card-height">
                        <div class="card-body">
                            <i class="fab fa-alipay fa-3x "></i>
                            <h3>與第三方支付合作</h3>
                            各大支付平台都可以支援喔！
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- TESTIMONIALS -->
  <section id="testimonials" class="p-4 bg-dark text-white">
      <div class="container">
          <h2 class="text-center">各家推薦</h2>
          <div class="row text-center">
              <div class="col">
                  <div class="slider">
                      <div>
                          <blockquote class="blockquote">
                              <p class="mb-0">
                                這是我第一次吃到這種味道，這道菜有著前所未有的新鮮味，溫潤的甜味，讓整盤菜更顯出奧妙....過熟柿子自然樸實的甜味，跟蔬菜的味道十分搭配，將青椒肉絲的嫩與脆襯托的更為出色，呈現出完美的青椒肉絲
                              </p>
                              <footer class="blockquote-footer">小當家
                                  <cite title="Company 1">中華一番</cite>
                              </footer>
                          </blockquote>
                      </div>
                      <div>
                        <blockquote class="blockquote">
                            <p class="mb-0">
                              這種濃郁的味道真不可思議，和極有嚼勁的麵搭配起來，讓人不禁想要一口接一口...
                            </p>
                            <footer class="blockquote-footer">小當家
                                <cite title="Company 2">中華一番</cite>
                            </footer>
                        </blockquote>
                    </div>
                    <div>
                        <blockquote class="blockquote">
                            <p class="mb-0">
                              所謂的料理啊，就是把自己的一切都盛進盤子裡。
                            </p>
                            <footer class="blockquote-footer">幸平城一郎
                                <cite title="Company 3">食戟之靈</cite>
                            </footer>
                        </blockquote>
                    </div>
                  </div>
              </div>
          </div>
      </div>
  </section>  



    <!-- FOOTER -->
    <footer id="main-footer" class="text-center p-4">
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
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
      integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
      crossorigin="anonymous"
    ></script>
    <script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
  </head>
    <script>
      // Get the current year for copyright
      $("#year").text(new Date().getFullYear());

    </script>
  </body>
</html>
