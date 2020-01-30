<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile Page</title>
</head>
<body>

	<h2>User Profile Page</h2>
	<table border="2" width="70%" cellpadding="2">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>JoinDate</th>
			<th>BirthDate</th>
			<th>Sex</th>
			<th>Phone</th>
			<th>Address</th>
			<th>VIP</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="user" items="${list}">
			<tr>
				<td>${user.userID}</td>
				<td>${user.profileFullName}</td>
				<td>${user.profileJoinDate}</td>
				<td>${user.profileBirthdate}</td>
				<td>${user.profileSex}</td>
				<td>${user.profilePhone}</td>
				<td>${user.profileAddress}</td>
				<td>${user.profileVIP}</td>
				<td><a href="editemp/${emp.id}">Edit</a></td>
				<td><a href="deleteemp/${emp.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>