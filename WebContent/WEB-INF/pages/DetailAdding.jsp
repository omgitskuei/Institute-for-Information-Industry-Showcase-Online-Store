<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="command" class="model.order.OrderBean" scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單管理_修改</title>
</head>
<body>
<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>

	<h1>新增明細</h1>
	
	<form:form method="POST" action="saveOrderDetails" modelAttribute="orderDetails">
		<table>
			<tr>
				<td></td>
				<td><form:hidden path="OrderDetailID" /></td>
			</tr>
			<tr>
				<td>訂單ID :</td>
				<td><form:input path="OrderID" /></td>
			</tr>
			<tr>
				<td>產品ID :</td>
				<td><form:input path="ProductID" /></td>
			</tr>
			<tr>
				<td>產品名稱 :</td>
				<td><form:input path="ProductName" /></td>
			</tr>
			<tr>
				<td>數量 :</td>
				<td><form:input path="ProductCount" /></td>
			</tr>
			<tr>
				<td>單價 :</td>
				<td><form:input path="ProductPrice" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:button cssClass="btn btn-info" type="submit" value="儲存">儲存</form:button></td>
			</tr>
		</table>
	</form:form>
	
	


