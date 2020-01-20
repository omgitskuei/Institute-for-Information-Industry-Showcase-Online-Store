<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<!-- 如果要重新登入的話要關瀏覽器才能重新Session -->
	<!-- 不然用無痕模式 -->
	<h2>首頁</h2>
	<hr>
	
	<p>
	歡迎光臨首頁
	</p>
	
	
	<hr>
	<!-- display user nams and role -->
	<p>
		使用者名稱: <security:authentication property="principal.username"/>
		<br><br>
		使用者身份: <security:authentication property="principal.authorities"/>
	</p>
	
	<hr>

	<!-- Add a link to point to /leaders ... this is for the managers -->
	
	<p>
		<a href="${pageCotext.request.contextPath}/leaders">LeaderShip Meeting</a>
		(Only for Manager peeps)
	</p>
	
	

	<hr>


	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
			method="POST">
		<input type="submit" value="登出" />
	
	</form:form>
	
</body>
</html>