<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
</head>
<body>
	This is the homepage Products User Login Search bar
	<form class="form" name="loginForm"
		action=<jstl:url value="controller.searchBar" /> method="post">

		<input type="search" title="Products search bar" name="SearchBar"></input>


Search bar
		<form class="form" name="searchForm" action=<jstl:url value="controller.searchBar" />
        method="post" >


	</form>

	<a href="<jstl:url value = "/controller.ProfileController"/>" > Show Profile </a>
</body>
</html>