<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Form</h3>
<form action="<c:url value="/hello.controller" />" method="get">
<!-- DIRECTS to /hello.controller via method POST -->
	<table>
		<tr>
			<td>Name:</td>
			<td><input type="search" name="name"></td>
			<td>${errors.name}</td>
		</tr>
		<tr>
			<td><input type="submit"></td>
		</tr>
	</table>
</form>