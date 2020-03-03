<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="command" class="model.order.OrderBean" scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單管理_修改</title>
</head>
<body >
	<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>

	<div class="container">
	<h1 class="mt-3">編輯訂單資料</h1>
	
	<form:form method="POST" action="saveOrder" modelAttribute="order">
		<table>
			<tr>
				<td>訂單編號 :</td>
				<td><form:input path="orderID" readonly="true"/></td>
			</tr>
			<tr>
				<td>訂購時間 :</td>
				<td><form:input path="OrderTime" readonly="true" /></td>
			</tr>
			<tr>
				<td>訂購人ID :</td>
				<td><form:input path="UserID" readonly="true" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:hidden path="Total" readonly="true" /></td>
			</tr>
			<tr>
				<td>寄送地址 :</td>
				<td><form:input path="MailingAddress" /></td>
			</tr>
			<tr>
				<td>寄送電話 :</td>
				<td><form:input path="MailingPhone" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:button cssClass="btn btn-info" type="submit" value="儲存">完成</form:button> 
					<input onclick="autofill()"	type="button" value="一鍵輸入" class="btn btn-primary" id="submit">
				</td>
			</tr>
		</table>
	</form:form>
	</div>
	<script>
		function autofill() {
			var productName = document.getElementById("MailingAddress");
			var productCategory = document.getElementById("MailingPhone");

			MailingAddress.value = "台中市霧峰區五福西路"
			MailingPhone.value = "0971-738-059";
		};
	</script>
</body>
</html>
