<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Sign In</title>
</head>
<body>
<h1>Admin Sign-In</h1>

<p>Please sign in to access the Administrator Content Management System</p>

<form action="<c:url value="/AdminLoginController.controller" />" method="post">
	<table>
		<!-- Email text field -->
		<tr>
			<td>Email:</td>
			<td><input type="text" name="userEmail"></td>
			<td>${errors.emailError}</td>
		</tr>
		<!-- Password text field -->
		<tr>
			<td>Password:</td>
			<td><input type="password" name="userPwd"></td>
			<td>${errors.pwdError}</td>
		</tr>
		<!-- Password text field -->
		<tr>
			<td>Remember Me:</td>
			<td><input type="checkbox" name="rememberMe"></td>
		</tr>
		<!-- Reset Button -->
		<tr>
			<td><input type="reset"></td>
		</tr>
		<!-- Submit Button -->
		<tr>
			<td><input type="submit"></td>
			<td>${errors.msgError}</td>
		</tr>
	</table>
</form>

</body>
</html>