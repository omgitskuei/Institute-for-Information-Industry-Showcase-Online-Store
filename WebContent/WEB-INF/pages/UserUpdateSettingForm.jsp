<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Noto+Sans+TC&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" integrity="sha256-HAaDW5o2+LelybUhfuk0Zh2Vdk8Y2W2UeKmbaXhalfA=" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <style>
    	<%@include file="/WEB-INF/css/bootstrap.css"%>
        <%@include file="/WEB-INF/css/style.css"%>
    </style>
<title>Farmville | 更改安全問題</title>
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
    <h2 class="my-3">使用者更改安全問題</h2>
    
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
		<li class="nav-item"><a class="nav-link" href="${updateLink}">使用者基本資料</a></li>
		<li class="nav-item"><a class="nav-link" href="${updatePasswordLink}">更改密碼</a></li>
		<li class="nav-item"><a class="nav-link active" href="${showTheUserSettingLink}">更改安全問題</a>
		<li class="nav-item"><a class="nav-link" href="${updateWalletLink}">查看電子錢包</a></li>
		<li class="nav-item"><a class="nav-link" href="${showTheUserOrderLink}">查看訂單</a></li>
	</ul>
	</div>
	</div>

<form method="POST" action="<jstl:url value="/UserProfile/updateSetting" />" >
		<div class="form-group">
		<input class="form-control" type="hidden" name="userID" value="${userSetting.userID}" />
		</div>
		
		<div class="form-group">
		<input class="form-control" type="hidden" name="settingID" value="${userSetting.settingID}" />
		</div>
		
		<div class="form-group">
		<label>您的名稱</label>
		<input class="form-control" name="settingDisplayName" value="${userSetting.settingDisplayName}" />
		</div>
		
		<div class="form-group"> 
		<label>請輸入您的安全問題</label>
		<input class="form-control" name="settingSecurityQ" value="${userSetting.settingSecurityQ}" />
		</div>
		
		<div class="form-group"> 
		<label>請輸入您的安全答案</label>
		<input class="form-control" name="settingSecurityA" value="${userSetting.settingSecurityA}" />
		</div>
		
		<div class="form-group"> 
		<label>是否同意使用資料</label>
		<select class="form-control" name="settingAllowMetadata">
		    <option value=true>是</option>
		    <option value=false>否</option>
		</select>
		</div>
		<div class="row d-flex justify-content-end">
		<div class="col-md-12">
		<button class="btn btn-success text-white" type="submit"
						>儲存</button>
						</div>
						</div>
		
		</form>
		
</div>
<script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>