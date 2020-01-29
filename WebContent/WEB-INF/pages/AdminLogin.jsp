<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
	<title>EEIT111 Team 1 Final Project - Admin Login</title>
	<!-- Stylesheet for HTML5 backward compatibility; normalize.css -->
	<!-- Dictate which Stylesheets to use for this webpage -->
    <style>
    	<%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    
</head>



<body>
	<h1>Admin Sign-In</h1>

	<p>Please sign in to access the Administrator Content Management System.</p>

	<form class="form" name="loginForm" action="<jstl:url value="/controller.AdminLoginController" />" method="post" id="myForm">
		
			<!-- Form input fields -->
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
		</form>

</body>

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