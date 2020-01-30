<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
  crossorigin="anonymous">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
  crossorigin="anonymous">
	<title>Admin's Login | 管理者登入頁</title>
	<!-- Stylesheet for HTML5 backward compatibility; normalize.css -->
	<!-- Dictate which Stylesheets to use for this webpage -->
    <style>
    	<%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    
</head>
<body>
 	<nav class="navbar navbar-expand-sm navbar-dark bg-dark p-0">
      <div class="container">
        <a href="index.html" class="navbar-brand">管理者</a>
      </div>
    </nav>

<!-- HEADER -->
<header id="main-header" class="py-2 bg-primary text-white">
  <div class="container">
    <div class="row">
      <div class="col-md-6">
        <h1><i class="fas fa-user"> FarmVille管理者系統</i></h1>
      </div>
    </div>
  </div>
</header>

<!-- ACTIONS -->
<section id="action" class="py-4 mb-4 bg-light">
  <div class="container">
    <div class="row">
      
    </div>
  </div>
</section>

	
		
<section id="login">
  <div class="container">
    <div class="row">
      <div class="col-md-6 mx-auto">
        <div class="card">
            <div class="card-header">
                <h4>使用者登入</h4>
            </div>
            <div class="card-body">
           <form class="form" name="loginForm" action="<jstl:url value="/controller.AdminLoginController" />" method="post" id="myForm">
            <div class="form-group">
                  <label for="email">電子郵件</label>
                  <input type="email" class="form-control" name="userEmail">
                  <span id="emailErrorSpan">${errors.emailError}</span>
                </div>
                <div class="form-group">
                  <label for="password">密碼</label>
                  <input type="password" class="form-control" name="userPwd">
                  <span id="pwdErrorSpan">${errors.pwdError}</span>
                </div>
                <div class="form-group ml-4">
                  <input class="form-check-input" type="checkbox" id="inlineFormCheck"/>
            	  <label class="form-check-label" for="FormCheck">
             		記住帳號密碼
            	  </label>
                </div>
                <input type="submit" value="Login" class="btn btn-primary btn-block">
            </form>
            </div>
        </div>
      </div>
    </div>
  </div>
</section>

<!-- FOOTER -->
<footer id="main-footer" class="bg-dark text-white mt-5 p-5">
  <div class="container">
    <div class="row">
      <div class="col">
        <p class="lead text-center">
          Copyright &copy;
          <span id="year"></span>
          FarmVille
        </p>
      </div>
    </div>
  </div>

</footer>
			
		<%-- 	<!-- Form input fields -->
			<label>Email Address:</label>
			<input type="email" id="myEmailInput" name="userEmail" placeholder="user@domain.com" inputmode="email" onblur="blurFunction()">
			<span id="emailErrorSpan">${errors.emailError}</span>
			<br></br>
			
			<label>Password:</label>
			<input type="password" id="myPwdInput" name="userPwd" onblur="blurFunction1()">
			<span id="pwdErrorSpan">${errors.pwdError}</span>
			<br></br>
			
			<label>Remember Me:</label>
			<input type="checkbox" name="rememberMe">
			<span></span>
			<br></br>
			
			<!-- Form buttons -->
			<label></label>
			<input type="submit" name="submitBtn" value="Submit">
			<span>${errors.notFoundError}</span>
			
			<label></label>
			<input type="reset" name="resetBtn">
			<span></span>
		</form> --%>

</body>

 <script src="http://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
    crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T"
    crossorigin="anonymous"></script>

  <script>
    // Get the current year for the copyright
    $('#year').text(new Date().getFullYear());

  </script>
<script>
// No focus = Changes the background color of input
function blurFunction() {
	var email1 = document.getElementById("myEmailInput").value;
	if (email1 === "") {
		document.getElementById("myEmailInput").style.background = "#CD5C5C";
	} else {
		document.getElementById("myEmailInput").style.background = "";
	}
}
function blurFunction1() {
	var pwd1 = document.getElementById("myPwdInput").value;
	if (pwd1 === "") {
		document.getElementById("myPwdInput").style.background = "#CD5C5C";
	} else {
		document.getElementById("myPwdInput").style.background = "";
	}
}
</script>


</html>