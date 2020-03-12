<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farmville | 使用者訂單</title>
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
	<c:url var="showTheUserSetitngLink" value="/AdminProfile/showTheUserSetting">
		<c:param name="userID" value="${user.userID}" />
	</c:url>
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link" href="${updateLink}">使用者基本資料</a></li>
		<li class="nav-item"><a class="nav-link" href="${updatePasswordLink}">更改密碼</a></li>
		<li class="nav-item"><a class="nav-link" href="${showTheUserSetitngLink}">查看安全設定</a></li>
		<li class="nav-item"><a class="nav-link" href="${updateWalletLink}">查看電子錢包</a></li>
		<li class="nav-item"><a class="nav-link active" href="${showTheUserOrderLink}">查看訂單</a></li>
	</ul>
	
	</div>
	
	
	

	<table class="table mt-4">
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
		        <jstl:forEach var="userOrder" items="${userOrder}">
	    <tbody>
		    <tr>
                <td><a class="btn btn-info" href='<c:url value='userDetails.do?orderID=${userOrder.orderID}' />' target="_blank">
                ${userOrder.orderID} 查看明細</a></td>
				<td>${userOrder.userID}</td>
				<td>${userOrder.total}</td>
				<td>${userOrder.mailingAddress}</td>
				<td>${userOrder.mailingPhone}</td>
				<td>${userOrder.orderTime}</td>
		    </tr>
	    </tbody>						<!-- construct an "update" link with customer id -->
				</jstl:forEach>
	</table>

</div>
</body>
</html>