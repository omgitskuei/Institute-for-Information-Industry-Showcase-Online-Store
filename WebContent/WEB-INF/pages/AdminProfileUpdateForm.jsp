<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="command" class="model.profile.ProfileBean" scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者編輯使用資訊</title>
</head>
<body>
<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>

	<h1>管理者編輯使用資訊</h1>

	<form:form method="POST" action="saveProfile" modelAttribute="profile">
		<table>
			<tr>
				<td></td>
				<td><form:hidden path="userID" /></td>
				<td></td>
			</tr>
			<tr>
				<td>姓名 :</td>
				<td><form:input path="profileFullName" /></td>
				
			</tr>
			<tr>
				<td>加入日期 :</td>
				<td><form:input path="profileJoinDate" /></td>
				<td><form:errors path="profileJoinDate" /></td>
			</tr>
			<tr>
				<td>生日 :</td>
				<td><form:input path="profileBirthdate" /></td>
				<td><form:errors path="profileBirthdate" /></td>
			</tr>
			<tr>
				<td>性別 :</td>
				<td><form:input path="profileSex" /></td>
			</tr>
			<tr>
				<td>電話 :</td>
				<td><form:input path="profilePhone" /></td>
				<td><form:errors path="profilePhone" /></td>
			</tr>
			<tr>
				<td>地址 :</td>
				<td><form:input path="profileAddress" /></td>
				<td><form:errors path="profileAddress" /></td>
			</tr>
			<tr>
				<td>VIP Level :</td>
				<td><form:input path="profileVIP" /></td>
				<td><form:errors path="profileVIP" /></td>
			</tr>

			<tr>
				<td>確認</td>
				<td><form:button cssClass="btn btn-info" type="submit" value="儲存">儲存</form:button></td>
			</tr>
		</table>
	</form:form>


