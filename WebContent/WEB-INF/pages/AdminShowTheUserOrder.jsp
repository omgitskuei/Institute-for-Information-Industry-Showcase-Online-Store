<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>

<div class="container">
<h1 class="mt-3">管理者管理單一使用者訂單</h1>

<div>
	<c:url var="updateLink" value="/AdminProfile/updateForm">
		<c:param name="userID" value="${user.userID}" />
	</c:url>
	<c:url var="updatePasswordLink" value="/AdminProfile/updatePasswordForm">
		<c:param name="userID" value="${user.userID}" />
	</c:url>
	<c:url var="updateWalletLink" value="/AdminProfile/updateWalletForm">
		<c:param name="userID" value="${user.userID}" />
	</c:url>
	<c:url var="showTheUserOrderLink" value="/AdminProfile/showTheUserOrer">
		<c:param name="userID" value="${user.userID}" />
	</c:url>
	<a href="${updateLink}">更新使用者基本資料</a>
	<a href="${updatePasswordLink}">更改密碼</a>
	<a href="${updateWalletLink}">更改電子錢包</a>
	<a href="${showTheUserOrderLink}">查看訂單</a>
	
	</div>
	
	
	

	<table class="table">
		<thead>
	    <tr>
	      <th scope="col">訂單 ID(OrderID):</th>
	      <th scope="col">使用者 ID</th>
	      <th scope="col">總金額</th>
	      <th scope="col">寄件地址</th>
	      <th scope="col">寄件電話</th>
	      <th scope="col">訂購時間</th>
	    </tr>
	    </thead>
		        <c:forEach var="userOrder" items="${userOrder}">
	    <tbody>
		    <tr>
		        <td>${userOrder.orderID}</td>
				<td>${userOrder.userID}</td>
				<td>${userOrder.total}</td>
				<td>${userOrder.mailingAddress}</td>
				<td>${userOrder.mailingPhone}</td>
				<td>${userOrder.orderTime}</td>
		    </tr>
	    </tbody>						<!-- construct an "update" link with customer id -->
				</c:forEach>
	</table>	

</div>
</body>
</html>