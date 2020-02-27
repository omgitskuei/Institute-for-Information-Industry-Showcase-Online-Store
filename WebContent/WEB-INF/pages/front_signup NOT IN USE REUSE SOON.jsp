<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
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
<title>註冊</title>
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
            <h1>註冊</h1>
            <p>註冊使用者帳號</p>
          </div>
        </div>
      </div>
      </div>
    </header>

    <!-- SIGNUP SECTION -->
<section id="contact" class="py-3">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="card p-4">
          <div class="card-body">
        <form action="" class="needs-validation" novalidate>
             <h3 class="text-center">請輸入帳號密碼</h3>
            <hr>
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label for="email">電子信箱</label>
                  <input type="text" class="form-control" placeholder="電子信箱：">
                </div>
              </div> 
              <div class="col-md-12">
                <div class="form-group">
                  <label for="password">密碼</label>
                  <input type="password" class="form-control" placeholder="密碼：">
                </div>
              </div> 
               <div class="col-md-12">
                <div class="form-group">
                  <label for="confirmpassword">確認密碼</label>
                  <input type="password" class="form-control" placeholder="確認密碼：">
                </div>
              </div> 
          </div>
            
            </div>
            
            <div class="mt-3 d-flex justify-content-end">
                <button type="reset" class="btn btn-secondary mr-2">清除</button>
                <button type="submit" class="btn btn-success">送出</button>
            </div>

        </form>
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
            <p>
              版權所有 &copy;
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
    <script>
      // Get the current year for copyright
      $("#year").text(new Date().getFullYear());
    </script>
  </body>
</html>
