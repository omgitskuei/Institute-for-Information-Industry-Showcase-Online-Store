<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
%>

<%@ taglib
	prefix="jstl"
	uri="http://java.sun.com/jsp/jstl/core"
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>EEIT111 Team 1 Final Project - Admin Index</title>
    <!-- Stylesheet for HTML5 backward compatibility; normalize.css -->

    <!-- Dictate which Stylesheets to use for this webpage -->
    <style>
    	<%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>


</head>

<body>
    <header>
        <h1>Administrator Portal</h1>
    </header>


    <main>
        <h2>URI: WebContent/AdminIndex</h2>

        <form class="form" name="loginForm" action=<jstl:url value="/controller.AdminIndexController" />
        method="post" >

        	<!-- Form buttons -->
        	<label>Click to Sign in as Admin:</label>
        	<input type="submit" title="Admin Login Button"name="adminSignInButton" value="Go to Admin Login">
        	<span></span>

        </form>

        <h3>Other files under URI: WebContent/...</h3>

        <a href='./form.jsp'>./form.jsp</a><br>
        <a href='./index.jsp'>./index.jsp</a><br>
        <a href='./loginSystem.jsp'>./loginSystem.jsp</a><br>
        <a href='./UploadFile.jsp'>./UploadFile.jsp</a><br>
        <a href='./viewImagePage.jsp'>./viewImagePage.jsp</a><br>


    </main>


    <footer>
        <p id=copyright>Copyright</p>
    </footer>
</body>


</html>