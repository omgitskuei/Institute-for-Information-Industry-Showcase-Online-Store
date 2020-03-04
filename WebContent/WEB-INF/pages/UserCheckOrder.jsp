<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>使用者查看訂單</title>
</head>
<body>

<%@include file="/WEB-INF/pages/front_navbar.jsp" %>

  <!-- PAGE HEADER -->
    <header id="page-header">
      <div class="dark-overlay-title"> 
        <div class="container">
            <div class="row">
                <div class="col-md-6 m-auto text-center">
                    <h1>會員中心</h1>
                </div>
            </div>
          </div>  
        </div>
    </header>
    <!-- PAGE HEADER -->
    
    <div class="container">
    
    <br>
    <!-- <h2>使用者查看訂單</h2> -->
    
    <!-- 選單 -->
    <jstl:url var="updateLink" value="/UserProfile/userUpdateForm">
		<jstl:param name="userID" value="${user.userID}" />
	</jstl:url>
	
	<jstl:url var="updatePasswordLink" value="/UserProfile/userUpdatePasswordForm">
		<jstl:param name="userID" value="${user.userID}" />
	</jstl:url>
	
	<jstl:url var="updateWalletLink" value="/UserProfile/showUserWallet">
		<jstl:param name="userID" value="${user.userID}" />
	</jstl:url>
	
	<jstl:url var="showTheUserOrderLink" value="/UserProfile/showTheUserOrder">
		<jstl:param name="userID" value="${user.userID}" />
	</jstl:url>
	
	<!-- <a href="${updateLink}">更新使用者基本資料</a>
	<a href="${updatePasswordLink}">更改密碼</a>
	<a href="${updateWalletLink}">查看電子錢包</a>
	<a href="${showTheUserOrderLink}">查看訂單</a> -->
	
<div class="dropdown show">
  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    請選擇要更新資料
  </a>

  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    <a class="dropdown-item" href="userUpdateForm">更新基本資料</a>
    <a class="dropdown-item" href="userUpdatePasswordForm">更改密碼</a>
    <a class="dropdown-item" href="showUserWallet">查看電子錢包</a>
  </div>
</div>
	<!-- 選單 -->
	
	
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
		        <jstl:forEach var="userOrder" items="${userOrder}">
	    <tbody>
		    <tr>
                <td><a  href='<c:url value='userDetails.do?orderID=${userOrder.orderID}' />' target="_blank">
                ${userOrder.orderID}</a></td>
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