<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>使用者修改密碼</title>
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
 <!-- <h3>使用者修改密碼</h3> -->
 
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
	<a href="${showTheUserSettingLink}">更改安全問題</a>
	<a href="${updateWalletLink}">查看電子錢包</a>
	<a href="${showTheUserOrderLink}">查看訂單</a> -->
	
<div class="dropdown show">
  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    請選擇要更新資料
  </a>

  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    <a class="dropdown-item" href="userUpdateForm">更新基本資料</a>
    <a class="dropdown-item" href="showTheUserSetting">更改安全問題</a>
    <a class="dropdown-item" href="showUserWallet">查看電子錢包</a>
    <a class="dropdown-item" href="showTheUserOrder">查看訂單</a>
  </div>
</div>
	<!-- 選單 -->
	
	
	<form method="POST" action="<jstl:url value="/AdminProfile/savePassword" />" >
		<div class="form-group">
		<input class="form-control" type="hidden" name="userID" value="${user.userID}" />
		</div>
		
		<div class="form-group">
		<label>您的帳號</label>
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
		<button class="btn btn-info" type="submit"
						>儲存</button>
		
		</form>
</div>
</body>
</html>