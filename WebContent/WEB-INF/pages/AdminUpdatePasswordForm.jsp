<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farmville | 更新密碼</title>
</head>
<body>
<!-- NAVBAR -->
<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>

	<div class="container">
	
	<h1 class="mt-3">管理者更新密碼</h1>
	
	
	
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
		<li class="nav-item"><a class="nav-link active" href="${updatePasswordLink}">更改密碼</a></li>
		<li class="nav-item"><a class="nav-link" href="${showTheUserSetitngLink}">查看安全設定</a></li>
		<li class="nav-item"><a class="nav-link" href="${updateWalletLink}">查看電子錢包</a></li>
		<li class="nav-item"><a class="nav-link" href="${showTheUserOrderLink}">查看訂單</a></li>
	</ul>
    

	</div>

<form method="POST" action="<c:url value="/AdminProfile/savePassword" />" >
		
		<div class="form-group">
		<input class="form-control" type="hidden" name="userID" value="${user.userID}" />
		</div>
		
		<div class="form-group">
		<label>帳號</label>
		<p>${user.userEmail}</p>
		</div>
		
		<div class="form-group"> 
		<label>請輸入您的密碼</label> <span style="color: red;" id="notFoundErrorSpan">${errors.mismatchError}</span>	
		<input class="form-control" name="currentPwd" placeholder="Enter your current password here" />
		</div>
		
		<div class="form-group">
		<label>請輸入您的新密碼</label> <span style="color: red;" id="notFoundErrorSpan">${errors.invalidError}</span>	
		<input class="form-control" name="newPwd" placeholder="Enter your new password here" /> 
		</div>
		<div class="row d-flex justify-content-end">
		
		<button class="btn btn-info" type="submit"
						>儲存</button>
		
		</form>
		</div>
</body>
</html>