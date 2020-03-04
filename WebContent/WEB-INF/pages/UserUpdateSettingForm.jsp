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
    <div class="dropdown show">
  <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    請選擇要更新資料
  </a>

  <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
    <a class="dropdown-item" href="userUpdatePasswordForm">更改密碼</a>
    <a class="dropdown-item" href="showTheUserSetting">更改安全問題</a>
    <a class="dropdown-item" href="showUserWallet">查看電子錢包</a>
    <a class="dropdown-item" href="showTheUserOrder">查看訂單</a>
  </div>
</div>
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