<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>使用者資料</title>
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
    <!-- <h2>使用者資料修改</h2> -->
    
    <!-- 選單 -->
    <jstl:url var="updateLink" value="/UserProfile/userUpdateForm">
		<jstl:param name="userID" value="${profile.userID}" />
	</jstl:url>
	
	<jstl:url var="updatePasswordLink" value="/UserProfile/userUpdatePasswordForm">
		<jstl:param name="userID" value="${profile.userID}" />
	</jstl:url>
	
	<jstl:url var="updateWalletLink" value="/UserProfile/showUserWallet">
		<jstl:param name="userID" value="${profile.userID}" />
	</jstl:url>
	
	<jstl:url var="showTheUserOrderLink" value="/UserProfile/showTheUserOrder">
		<jstl:param name="userID" value="${profile.userID}" />
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
    <a class="dropdown-item" href="userUpdatePasswordForm">更改密碼</a>
    <a class="dropdown-item" href="showUserWallet">查看電子錢包</a>
    <a class="dropdown-item" href="showTheUserOrder">查看訂單</a>
  </div>
</div>
	
	<!-- 選單 -->
	
		   <form:form method="POST" action="userUpdateProfile" modelAttribute="profile">
				<div class="form-group">
				<form:hidden path="userID" />
				</div>
				
				<div class="form-group">
				<form:hidden path="profileID" />
				</div>
				
				<div class="form-group">
				<label>姓名</label>
				<form:input cssClass="form-control" path="profileFullName" />
				</div>
				
				<div class="form-group">
				<label>加入日期</label>
				<form:input  cssClass="form-control" path="profileJoinDate" readonly="true" />
				<small><span class="errMsgJS" id="joinDateErrMsg"></span></small>
				<form:errors class="errMsgJS" path="profileJoinDate" />
				</div>
				
				<div class="form-group">
				<label for="birthday">生日</label>
				<form:input type="date" name="birthday" id="birthdayInput" onkeyup="birthdayCheck()" cssClass="form-control" path="profileBirthdate" />
				<small><span class="errMsgJS" id="joinDateErrMsg"></span></small>
				<form:errors class="errMsgJS" path="profileBirthdate" />
				</div>
				
				<div class="form-group">
				<label>性別</label>
				<form:select cssClass="form-control" path="profileSex" >
					<form:option value="f">女性(Female)</form:option>
					<form:option value="m">男性(Male)</form:option>
				</form:select>
				</div>
				
				<div class="form-group">
				<label>電話</label>
				<form:input cssClass="form-control" path="profilePhone" />
				<form:errors class="errMsgJS" path="profilePhone" />
				<small><span class="errMsgJS" id="phoneErrMsg"></span></small>
				</div>
				
				<div class="form-group">
				<label>地址</label>
				<form:input cssClass="form-control" path="profileAddress" />
				<form:errors class="errMsgJS" path="profileAddress" />
				<small><span class="errMsgJS" id="addressErrMsg"></span></small>
				</div>
				
				<div class="form-group">
				<label>VIP Level :</label>
				<form:input cssClass="form-control" path="profileVIP" readonly="true" />
				</div>
				
				<form:button class="btn btn-info" type="submit"
							 value="儲存"
								>儲存</form:button>
			</form:form>
      </div>
      
<!-- <script>
    document.getElementById("userInfo").onchange = function() {
        if (this.selectedIndex!==0) {
            window.location.href = this.value;
        }        
    };
</script> -->
<script type="text/javascript">
function handleSelect(elm)
{
window.location = elm.value+".jsp";
}
</script>
      
</body>
</html>