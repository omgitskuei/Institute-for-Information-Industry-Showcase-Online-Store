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
   <link rel="stylesheet" href="css/style.css" />
   <%--  <style>
    	<%@include file="/WEB-INF/css/bootstrap.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style> --%>
  <title>忘記密碼</title>
  </head>
  <body>
    <!-- START -->
 <%@include file="/WEB-INF/pages/front_navbar.jsp" %>
    <!-- PAGE HEADER -->
    <header id="page-header" >
      <div class="dark-overlay-title">
      <div class="container">
        <div class="row">
          <div class="col-md-6 m-auto text-center">

            <h1>密碼</h1>

          </div>
        </div>
      </div>
    </div>
    </header>

    <!-- EMAIL VERIFICATION SECTION -->
<section class="py-3">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card p-4">
          <div class="card-body">
            <form 
            	class="form" 
                name="signupForm"
				action="<jstl:url value="/userSignUpVerification" />" 
				method="post">
            	<h3 class="text-center">輸入驗證碼</h3>
				<hr>
            	<div class="row">
					<div class="col-md-12">
 						<div class="form-group">
							<label for="confirmCode">請輸入信箱的驗證碼</label>
							<input type="text" class="form-control" placeholder="驗證碼：" name="confirmCode">
							<span id="codeErrorSpan" style="color: red;" class="ml-auto">${errors.codeError}</span>
						</div>
					</div> 
					<div class="col-md-12">
						<div class="form-group">
							<button type="submit" class="btn btn-success btn-block text-white" value="送出">送出驗證碼</button>
						</div>
					</div>
				</div>
			</form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

    <!-- FOOTER -->
    <footer id="main-footer" class="text-center p-4 bg-dark text-white">
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
