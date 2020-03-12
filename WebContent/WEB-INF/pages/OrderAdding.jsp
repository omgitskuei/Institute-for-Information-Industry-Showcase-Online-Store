<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="command" class="model.order.OrderBean" scope="request"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>訂單管理_修改</title>
</head>
<body >
	<!-- NAVBAR -->
	<%@include file="/WEB-INF/pages/AdminNavbar.jsp"%>

	<div class="container">
	<h1 class="mt-3">編輯訂單資料</h1>
	
	<form:form method="POST" action="saveOrder" modelAttribute="order">
		 <div class="from-group">
		<form:hidden cssClass="form-control" path="orderID" />
		</div>

		<div class="from-group">
		<label>訂購時間</label>
		<form:input cssClass="form-control" path="OrderTime" />
		</div>

		<div class="from-group">
			<label>訂購人 ID</label>
			<form:input cssClass="form-control" path="UserID" />
		</div>

		<div class="from-group">
			<form:hidden cssClass="form-control" path="Total" readonly="true"  />
		</div>
		
		<div class="from-group">
			<label>寄送地址</label>
			<form:input cssClass="form-control" path="MailingAddress" />
		</div>

		<div class="from-group">
			<label>寄送電話</label>
			<form:input cssClass="form-control" path="MailingPhone" />
		</div>

			
		<div class="from-group mt-3">
		<div class="row d-flex justify-content-end">
				<div class="col-md-12">
		<form:button class="btn btn-secondary" type="submit" value="儲存" >下一步</form:button>
				<input onclick="autofill()"	type="button" value="一鍵輸入" class="btn btn-primary" id="submit">
				</div>
			</div>
		</div>
	</form:form>
	</div>
	<script>
		function autofill() {
			var productName = document.getElementById("MailingAddress");
			var productCategory = document.getElementById("MailingPhone");

			MailingAddress.value = "台中市霧峰區五福西路"
			MailingPhone.value = "0971-738-059";
		};
	</script>
</body>
</html>
