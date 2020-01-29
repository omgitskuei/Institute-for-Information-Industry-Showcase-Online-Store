<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
	<title>EEIT111 Team 1 Final Project - Admin Dashboard</title>
	<!-- Stylesheet for HTML5 backward compatibility; normalize.css -->
	<!-- Dictate which Stylesheets to use for this webpage -->
    <style>
    	<%@include file="/WEB-INF/css/normalize.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
    
<!--     Dedicated Javascript for this webpage -->
<!--     <script type="text/javascript" src="./AdminIndexJS.js"></script> -->

</head>


<body>
	<h1>Account Dashboard</h1>
	<h2>Welcome, <br />${loggedInUserEmail} ${loggedInUserPwd}</h2>
	
	<a href="<jstl:url value = "/controller.AdminUsersManagementController"/>" method='<jstl:redirect></jstl:redirect>'> Users Management </a>
	
	<a href="<jstl:url value = "/controller.AdminProductsManagementController"/>" > Product Inventory Management </a>
	
	<a href="<jstl:url value = "/controller.AdminIndexController"/>" > Sign out </a>

</body>
</html>

