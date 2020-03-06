<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
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
   <title>聯絡我們</title>
  </head>
  <body>
    <!-- START -->
 <%@include file="/WEB-INF/pages/front_navbar.jsp" %>

    <!-- PAGE HEADER -->
    <header id="page-header">
      <div class="dark-overlay-title">
        <div class="container">
            <div class="row">
                <div class="col-md-6 m-auto text-center">
                    <h1>聯絡我們</h1>
                    <!--<p>有任何問題歡迎與我們聯絡... </p>-->
                </div>
            </div>
        </div>
      </div>
    </header>
<!-- CONTACT SECTION -->
<section id="contact" class="py-3">
  <div class="container">
    <div class="row">
      <div class="col-md-4">
        <div class="card p-4">
          <div class="card-body">
            <h4>Get In Touch</h4>
            <p>If you have any story to tell or would like to hear some of our company you can do so via the form: </p>
            <h4>地址：</h4>
            <p>5867 W Walbrook Dr. 95129 San Jose, CA</p>
            <h4>電子信箱：</h4>
            <p>farmvilletaiwan@gmail.com</p>
            <h4>電話：</h4>
            <p>(408)366-18888</p>
          </div>
        </div>
      </div>
      <!-- CONTACT US FORM -->
     
      <div class="col-md-8">
        <div class="card p-4">
          <div class="card-body">
           <form action="<jstl:url value="/directFrontContactUs"/>" method=post>
            <h3 class="text-center">請在下方輸入您的意見</h3>
            <hr>
            <div class="row">
              <!-- EMAIL INPUT -->
              <div class="col-md-12">
                <div class="form-group"> 
                  <input 
                  	type="email" 
                  	class="form-control" 
                  	placeholder="電子信箱："
                  	name="inputEmail"
                  	id="userEmail"
                  	>
                  <span style="color: red;">${errors.emailError}</span>
                </div>
              </div> 
              <!-- NAME INPUT -->
              <div class="col-md-12">
                <div class="form-group">
                  <input 
                  	type="text" 
                  	class="form-control" 
                  	placeholder="姓名："
                  	name="inputName"
                  	id="userName"
                  	>
                  <span style="color: red;">${errors.nameError}</span>
                </div>
              </div> 
              <!-- CATEGORY INPUT -->
              <div class="col-md-12">
                <div class="form-group">
                  <select class="form-control" id="inputCategory" name="inputCategory">
                      <option>訂單問題</option>
                      <option>帳號問題</option>
                      <option>付款問題</option>
                      <option>商品問題</option>
                      <option>退換貨問題</option>
                      <option>其他問題</option>
                  </select>
                  <span style="color: red;">${errors.categoryError}</span>
                </div>
              </div>
            </div>
            <div class="row">
              <!-- MESSAGE INPUT -->
              <div class="col-md-12">
                <div class="form-group">
                  <textarea
                  	name="inputMessage"
                  	id="inputMessage"
                  	cols="30"
                  	rows="10"
                  	class="form-control"
                  	placeholder="內容"></textarea><span id="messageErrorSpan" style="color: red;">${errors.messageError}</span>
                </div>
              </div>
              <!-- SUBMIT BUTTON -->
              <div class="col-md-12">
                <div class="form-group">
                  <input type="submit" value="送出" class="btn btn-success text-white btn-block" onclick="myFunction()">
                  <input onclick="autofill()" type="button" value="一鍵輸入" class="btn btn-secondary btn-block" id="submit">
                </div>
                <h5 class="text-center">我們將儘快與您聯繫</h5>
              </div>
            </div>
             </form>
          </div>
        </div>
      </div>
     
    </div>
  </div>
</section>

<script>
// function myFunction() {
// 	var textArea = document.getElementById("inputMessage");
// 	alert("wow");
// 	if (textArea.innerHTML.length === 0) {
// 		document.getElementById("messageErrorSpan").innerHTML = "Can't be empty";
// 	}
// }
</script>

<!-- STAFF -->
<section id="staff" class="py-5 text-center bg-dark text-white">
  <div class="container">
    <h1>我們的員工</h1>
    <hr>
    <div class="row mb-2">
      <div class="col-md-4">
        <img src="https://i.imgur.com/6xmTE3n.jpg" alt="" class="img fluid rounded-circle mb-2" style="height: 200px; width: 200px;">
        <h4>Chris Tung</h4>
        <p>本公司COO－首席營運長，健康蔬果網站發想者，致力於讓健康蔬果經由網路的便利，走進消費者的生活。</p>
      </div>
      <div class="col-md-4">
        <img src="https://i.imgur.com/cpOSqoL.jpg" alt="" class="img fluid rounded-circle mb-2" style="height: 200px; width: 200px;">
        <h4>Thomas Lu</h4>
        <p>本公司工程師，維護網頁美觀與資訊，讓新鮮蔬果的最新資訊，詳實呈現在消費者眼前。</p>
      </div>
      <div class="col-md-4">
        <img src="https://i.imgur.com/hqe3GF9.jpg" alt="" class="img fluid rounded-circle mb-2" style="height: 200px; width: 200px;">
        <h4>Jerry Tai</h4>
        <p>本公司CTO－首席技術長，致力維持網站的穩定，讓消費者得以選購到所要的蔬果。</p>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4">
        <img src="https://i.imgur.com/UmQdsfi.jpg" alt="" class="img fluid rounded-circle mb-2" style="height: 200px; width: 200px;">
        <h4>游忠城</h4>
        <p>負責本公司各項蔬果通路營運，有著對市場變動的高度敏感，致力取得對消費者最友善價格。</p>
      </div>
      <div class="col-md-4">
        <img src="https://i.imgur.com/VlJYeaf.jpg" alt="" class="img fluid rounded-circle mb-2" style="height: 200px; width: 200px;">
        <h4>Ming Juang</h4>
        <p>客服主管，秉持消費者如家人的初衷，處理每一筆消費者的疑問與意見。</p>
      </div>
      <div class="col-md-4">
        <img src="https://i.imgur.com/c2xAsfw.jpg" alt="" class="img fluid rounded-circle mb-2" style="height: 200px; width: 200px;">
        <h4>Alex Ku</h4>
        <p>負責蔬果各項送驗與紀錄管理，務求讓消費者吃得安心。</p>
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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js" integrity="sha256-NXRS8qVcmZ3dOv3LziwznUHPegFhPZ1F/4inU7uC8h0=" crossorigin="anonymous"></script>
    <script>
      // Get the current year for copyright
      $("#year").text(new Date().getFullYear());

    </script>
    <script>
	function autofill() {
		var eml = document.getElementById("userEmail");
		var name = document.getElementById("userName");
		var category = document.getElementById("inputCategory");
		var text = document.getElementById("inputMessage");
		eml.value = "farmvilletaiwan@gmail.com";
		name.value = "王大明";
		category.value = "退換貨問題";
		text.value = "我把紅蘿蔔買成白蘿蔔了！怎麼辦啊？我拆了外包裝，還可以換貨嗎？"
	};
    </script>
  </body>
</html>
