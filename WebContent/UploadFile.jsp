<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File Page</title>
</head>
<body>
	<form action="<c:url value="/uploadId.controller"></c:url>" method="post" enctype="multipart/form-data">		
		Please Select Your Picture To Upload:<br /> 
		<input type="file" name="myFiles"/> 
		<input type="submit" value="Upload" />
	</form>
</body>
</html>