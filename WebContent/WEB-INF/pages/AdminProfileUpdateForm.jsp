<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="command" class="model.profile.ProfileBean"
	scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farmville | 編輯使用者資訊</title>
<style>.errMsgJS {
  color: red;
}
</style>
</head>
<body>
	<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>
	
	<div class="container">
	<h1 class="mt-3">管理者編輯使用資訊</h1>
	<div>
	<c:url var="updateLink" value="/AdminProfile/updateForm">
		<c:param name="userID" value="${profile.userID}" />
	</c:url>
	<c:url var="updatePasswordLink" value="/AdminProfile/updatePasswordForm">
		<c:param name="userID" value="${profile.userID}" />
	</c:url>
	<c:url var="updateWalletLink" value="/AdminProfile/updateWalletForm">
		<c:param name="userID" value="${profile.userID}" />
	</c:url>
	<c:url var="showTheUserOrderLink" value="/AdminProfile/showTheUserOrer">
		<c:param name="userID" value="${profile.userID}" />
	</c:url>
	<c:url var="showTheUserSetitngLink" value="/AdminProfile/showTheUserSetting">
		<c:param name="userID" value="${profile.userID}" />
	</c:url>
	
	
	<ul class="nav nav-tabs">
		<li class="nav-item"><a class="nav-link active" href="${updateLink}">使用者基本資料</a></li>
		<li class="nav-item"><a class="nav-link" href="${updatePasswordLink}">更改密碼</a></li>
		<li class="nav-item"><a class="nav-link" href="${showTheUserSetitngLink}">查看安全設定</a></li>
		<li class="nav-item"><a class="nav-link" href="${updateWalletLink}">查看電子錢包</a></li>
		<li class="nav-item"><a class="nav-link" href="${showTheUserOrderLink}">查看訂單</a></li>
	</ul>
	
	</div>

	<form:form method="POST" action="updateProfile" modelAttribute="profile">
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
		<form:input cssClass="form-control" path="profileVIP" />
		<form:errors class="errMsgJS" path="profileVIP" />
		</div>
		
		<form:button class="btn btn-info" type="submit"
						value="儲存">儲存</form:button>
			
	</form:form>
	</div>

	<script src="https://kit.fontawesome.com/c3dc04dc4d.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

	<script>
		function joinDateCheck() {
			var dateValue = document.getElementById('joinDateInput').value;
			var dateCon = document.getElementById('joinDateErrMsg');
			var rexdate = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;
			if (dateValue == '') {
				dateCon.innerHTML = "日期不可空白";
			} else if (!rexdate.test(dateValue)) {
				dateCon.innerHTML = "請注意格式:西元年-月-日, 例如: yyyy-MM-dd";
			} else {
				dateCon.innerHTML = "";
			}
		}
		function birthdayCheck() {
			var dateValue = document.getElementById('birthdayInput').value;
			var dateCon = document.getElementById('birthdayErrMsg');
			var rexdate = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;
			if (dateValue == '') {
				dateCon.innerHTML = "日期不可空白";
			} else if (!rexdate.test(dateValue)) {
				dateCon.innerHTML = "請注意格式:西元年-月-日, 例如: yyyy-MM-dd";
			} else {
				dateCon.innerHTML = "";
			}
		}
	</script>
</body>
</html>
