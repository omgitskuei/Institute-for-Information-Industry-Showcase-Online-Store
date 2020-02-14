<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="command" class="model.profile.ProfileBean"
	scope="request"></jsp:useBean>

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
	<div>
	<c:url var="updateLink" value="/AdminProfile/updateForm">
		<c:param name="userID" value="${user.userID}" />
	</c:url>
	<c:url var="updatePasswordLink" value="/AdminProfile/updatePasswordForm">
		<c:param name="userID" value="${user.userID}" />
	</c:url>
	<a href="${updateLink}">更新使用者基本資料</a>
	<a href="${updatePasswordLink}">更改密碼</a>
	
	
	<a>使用者基本資料</a>
	<a>使用者基本資料</a>
	<a>使用者基本資料</a>
	</div>

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
				<td><form:input id="joinDateInput" onchange="joinDateCheck()"
						path="profileJoinDate" /></td>
				<td><form:errors path="profileJoinDate" /></td>
				<td><span id="joinDateErrMsg"></span></td>
			</tr>
			<tr>
				<td>生日 :</td>
				<td><form:input id="birthdayInput" onchange="birthdayCheck()"
						path="profileBirthdate" /></td>
				<td><form:errors path="profileBirthdate" /></td>
				<td><span id="birthdayErrMsg"></span></td>
			</tr>
			<tr>
				<td>性別 :</td>
				<td><form:input path="profileSex" /></td>
			</tr>
			<tr>
				<td>電話 :</td>
				<td><form:input id="phoneInput" path="profilePhone" /></td>
				<td><form:errors path="profilePhone" /></td>
				<td><span id="phoneErrMsg"></span></td>
			</tr>
			<tr>
				<td>地址 :</td>
				<td><form:input onblur="addressCheck()" path="profileAddress" /></td>
				<td><form:errors path="profileAddress" /></td>
			</tr>
			<tr>
				<td>VIP Level :</td>
				<td><form:input onblur="VIPCheck()" path="profileVIP" /></td>
				<td><form:errors path="profileVIP" /></td>
			</tr>
		

			<tr>
				<td>確認</td>
				<td><form:button cssClass="btn btn-info" type="submit"
						value="儲存">儲存</form:button></td>
			</tr>
		</table>
	</form:form>
	



	<script>
		function joinDateCheck() {
			var dateValue = document.getElementById('joinDateInput').value;
			var dateCon = document.getElementById('joinDateErrMsg');
			var rexdate = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;

			if (dateValue == '') {
				dateCon.innerHTML = "日期不可空白";
			} else if (!rexdate.test(dateValue)) {
				dateCon.innerHTML = "請注意格式:西元年-月-日, 例如: yyyy-MM-dd";
			} else {
				dateCon.innerHTML = "";
			}
		}

		function birthdayCheck() {
			var dateValue = document.getElementById('birthdayInput').value;
			var dateCon = document.getElementById('birthdayErrMsg');
			var rexdate = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;

			if (dateValue == '') {
				dateCon.innerHTML = "日期不可空白";
			} else if (!rexdate.test(dateValue)) {
				dateCon.innerHTML = "請注意格式:西元年-月-日, 例如: yyyy-MM-dd";
			} else {
				dateCon.innerHTML = "";
			}
		}
	</script>
</body>
</html>

