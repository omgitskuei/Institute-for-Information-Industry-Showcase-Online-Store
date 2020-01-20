<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Custom Login Page</title>

<style>
	.failed {
		color: red;
	}
</style>
</head>
<body>
<h3>請輸入帳號密碼：</h3>
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser"
			   mathod="POST">
		<!-- Check for login error -->
		
		<c:if test="${param.error != null}">
			<i class="failed">您輸入的帳號/密碼錯誤請重新輸入</i>
		</c:if>
		
		<p>
			使用者名稱: <input type="text" name="username"/>
		</p>
		<p>
			密碼: <input type="password" name="password"/>
		</p>
	
		<input type="submit" value="登入"/>
	</form:form>
</body>
</html>