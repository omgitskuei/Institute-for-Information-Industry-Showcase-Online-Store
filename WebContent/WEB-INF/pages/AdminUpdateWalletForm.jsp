<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farmville | 修改電子錢包</title>
</head>
<body>

<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>

    <div class="container">
    
      <h1 class="mt-3">管理員修改電子錢包</h1>

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
		<li class="nav-item"><a class="nav-link active" href="${updateWalletLink}">查看電子錢包</a></li>
		<li class="nav-item"><a class="nav-link" href="${showTheUserOrderLink}">查看訂單</a></li>
	</ul>
	
		
     	
        <p>目前: ${wallet.walletAmount}</p>

		<form method="POST" action="<c:url value="/AdminProfile/saveWallet" />" >
		<input hidden="true" name="userID" value="${user.userID}" />
		
		<div class="form-group">
		<label>帳號</label>
		<p>${user.userEmail}</p>
		</div>
		
		<div class="form-group">
		<label>修改金額: </label>
		<input class="form-control" name="newwalletAmount" value="${wallet.walletAmount}" />
		</div>
		
		<button class="btn btn-info" type="submit"
						value="儲存">儲存</button>
		</form>

    </div>  

</body>
</html>