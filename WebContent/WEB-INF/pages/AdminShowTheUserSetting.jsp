<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farmville | 使用者設定 Setting</title>
</head>
<body>
<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>
	
	<div class="container">
    <div class="row d-flex justify-content-center">
    <div class="col-md-12">
    <h2 class="my-3">管理者更改使用者安全問題</h2>
    
    <!-- 選單 -->
    
     <jstl:url var="updateLink" value="/AdminProfile/updateForm">
		<jstl:param name="userID" value="${userSetting.userID}" />
	</jstl:url>

	<jstl:url var="updatePasswordLink" value="/AdminProfile/updatePasswordForm">
		<jstl:param name="userID" value="${userSetting.userID}" />
	</jstl:url>

	<jstl:url var="updateWalletLink" value="/AdminProfile/updateWalletForm">
		<jstl:param name="userID" value="${userSetting.userID}" />
	</jstl:url>

	<jstl:url var="showTheUserOrderLink" value="/AdminProfile/showTheUserOrer">
		<jstl:param name="userID" value="${userSetting.userID}" />
	</jstl:url>

	<jstl:url var="showTheUserSettingLink" value="/AdminProfile/showTheUserSetting">
		<jstl:param name="userID" value="${userSetting.userID}" />
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

<form method="POST" action="<jstl:url value="/AdminProfile/updateSetting" />" >
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
		<button class="btn btn-info" type="submit"
						>儲存</button>
		
		</form>
		
</div>
<script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
	</div>
</body>
</html>