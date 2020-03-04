<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>使用者更新安全問題</title>
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
 <!-- 選單 -->
 <div class="userMenu">
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
	
	<a href="${updateLink}">更新使用者基本資料</a>
	<a href="${updatePasswordLink}">更改密碼</a>
	<a href="${showTheUserSettingLink}">更改安全問題</a>
	<a href="${updateWalletLink}">查看電子錢包</a>
	<a href="${showTheUserOrderLink}">查看訂單</a>
	<!-- 選單 -->
</div>
<h3>使用者更新安全問題</h3>

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
		<button class="btn btn-info" type="submit"
						>儲存</button>
		
		</form>
</div>
</body>
</html>