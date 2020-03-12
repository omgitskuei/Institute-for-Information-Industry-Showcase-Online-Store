<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farmville | 修改密碼</title>
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

<div class="row d-flex justify-content-center">
<div class="col-md-12">
 <h2 class="my-3">使用者修改密碼</h2>


 
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
	
	<jstl:url var="showTheUserSettingLink" value="/UserProfile/showTheUserSetting">
		<jstl:param name="userID" value="${user.userID}" />
	</jstl:url>

	<ul class="nav nav-tabs">
	<li class="nav-item"><a href="${updateLink}" class="nav-link">使用者基本資料</a></li>
	<li class="nav-item"><a href="${updatePasswordLink}" class="nav-link active">更改密碼</a></li>
	<li class="nav-item"><a class="nav-link" href="${showTheUserSettingLink}">更改安全問題</a>
	<li class="nav-item"><a href="${updateWalletLink}" class="nav-link">查看電子錢包</a></li>
	<li class="nav-item"><a href="${showTheUserOrderLink}" class="nav-link">查看訂單</a></li>
	</ul>
	<!-- 選單 -->
	</div>
</div>

	
	
	<form method="POST" action="<jstl:url value="/UserProfile/savePassword" />" >
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
		<div class="row d-flex justify-content-end">
		<button class="btn btn-success text-white" type="submit">儲存</button>
		</div>
		
		</form>
</div>
<script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>