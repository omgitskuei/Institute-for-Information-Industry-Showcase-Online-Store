<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Orders Management Page</title>
</head>
<body>
	<h2>OrdersManagement</h2>
	<table>
		<tr>
			<th>orderID</th>
			<th>userID</th>
			<th>total</th>
			<th>mailingAddress</th>
			<th>mailingPhone</th>
			<th>orderTime</th>
		</tr>
		<c:foreach var="orderInfo" items="${orderList}">
			<tr>
				<td>${orderInfo.orderID}</td>
				<td>${orderInfo.userID}</td>
				<td>${orderInfo.total}</td>
				<td>${orderInfo.mailingAddress}</td>
				<td>${orderInfo.mailingPhone}</td>
				<td>${orderInfo.orderTime}</td>
			</tr>
		</c:foreach>
	</table>
</body>
</html>