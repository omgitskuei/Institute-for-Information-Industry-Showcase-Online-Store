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

  <title>Navbar</title>
  </head>
  <body>
 <!-- START -->
 
<!-- NAVBAR -->
 <nav class="navbar navbar-expand-sm navbar-dark ">
      <div class="container">
			<a class="navbar-brand" href=<jstl:url value="/directhomepage"/> style="font-family: 'Kaushan Script', cursive;">FarmVille</a>
	<button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav navbar-left">
            <li class="nav-item first">
              <a class="nav-link" href=<jstl:url value="/directaboutus"/>>關於我們</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href=<jstl:url value="/directservices"/>>商品</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href=<jstl:url value="/directcontact"/>>聯絡我們</a>
            </li>
          </ul>
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
              <i class="fa fa-shopping-cart" style="display: inline-block; color: #3E5902;"></i>
              <a href=<jstl:url value="/directshoppingcart"/> class="nav-link" style="display: inline-block;">購物車</a>
            </li>
            
            <!-- 如果有登入 才顯示會員中心 -->
            <jstl:if test="${cookie.containsKey('loginSuccessCookie')}">
				<li class="nav-item"><a class="nav-link" href=<jstl:url value="/UserProfile/userUpdateForm"/>>會員中心</a></li>
			</jstl:if>
            
            <!-- 如果為空 才顯示 -->
            <jstl:if test="${empty cookie.loginSuccessCookie}">
				<li class="nav-item"><a class="nav-link" href=<jstl:url value="/directlogin"/>>登入</a></li>
			</jstl:if>
			<!-- 如果不為空 才顯示 -->
            <jstl:if test="${cookie.containsKey('loginSuccessCookie')}">
				<li class="nav-item"><a class="nav-link" href=<jstl:url value="/directLogout"/>>登出</a></li>
			</jstl:if>
			
			
          </ul>
        </div>
      </div>
    </nav>

 <!-- <nav class="navbar navbar-expand-sm navbar-dark">
      <div class="container">
        <a href=<jstl:url value="/directhomepage"/> class="navbar-brand" style="font-family: 'Kaushan Script', cursive;">FarmVille</a>
        <button
          class="navbar-toggler"
          data-toggle="collapse"
          data-target="#navbarCollapse"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item first">
              <a href=<jstl:url value="/directaboutus"/> class="nav-link">關於我們</a>
            </li>
            <li class="nav-item">
              <a href=<jstl:url value="/directservices"/> class="nav-link">服務</a>
            </li>
            <li class="nav-item">
              <a href=<jstl:url value="/directcontact"/> class="nav-link">聯絡我們</a>
            </li>
            <li class="nav-item">
              <a href=<jstl:url value="/directshoppingcart"/> class="nav-link">購物車</a>
            </li>
            <li class="nav-item">
              <a href=<jstl:url value="/directlogin"/> class="nav-link">登入</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
 -->
 


    
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
      src="https://kit.fontawesome.com/c3dc04dc4d.js"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"
      integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
      crossorigin="anonymous"
    ></script>
    <script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
  </head>
  </body>
</html>
