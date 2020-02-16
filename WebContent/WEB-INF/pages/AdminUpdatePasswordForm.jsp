<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者更新密碼</title>
</head>
<body>
<!-- NAVBAR -->
<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>
	<h1>管理者更新密碼</h1>
	<div>
		<c:url var="updateLink" value="/AdminProfile/updateForm">
			<c:param name="userID" value="${user.userID}" />
		</c:url>
		<c:url var="updatePasswordLink"
			value="/AdminProfile/updatePasswordForm">
			<c:param name="userID" value="${user.userID}" />
		</c:url>
		<a href="${updateLink}">更新使用者基本資料</a> 
		<a href="${updatePasswordLink}">更改密碼</a>


		<a>使用者基本資料</a> <a>使用者基本資料</a> <a>使用者基本資料</a>
	</div>

<form method="POST" action="<c:url value="/AdminProfile/savePassword" />" >
		<table>
			<tr>
				<td></td>
				<td><input type="hidden" name="userID" value="${user.userID}" /></td>
				<td></td>
			</tr>
			<tr>
				<td>帳號: </td>
				<td>${user.userEmail}</td>
				<td></td>
			</tr>
			<tr>
				<td>密碼: </td>
				<td><input  name="newPwd" value="${user.userPwd}"/></td>
			</tr>
			<tr>
				<td>確認</td>
				<td><button class="btn btn-info" type="submit"
						>儲存</button></td>
			</tr>
		</table>
		</form>
</body>
</html>