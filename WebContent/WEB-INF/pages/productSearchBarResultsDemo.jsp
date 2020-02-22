<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>product</title>

<style>
hr { 
  display: block;
  margin-top: 0.5em;
  margin-bottom: 0.5em;
  margin-left: auto;
  margin-right: auto;
  border-style: inset;
  border-width: 1px;
} 
</style>

</head>
<body>

<h1>Inventory, Query Results</h1>
<h2>User input: </h2>
<h2></h2>

<c:forEach var="product" items="${InventoryList}" varStatus="status">
	<!-- construct an "update" link with customer id -->
	<c:url var="updateLink" value="/AdminProduct/updateForm">
		<c:param name="productID" value="${product.productID}" />
	</c:url>

	<!-- construct an "delete" link with customer id -->
	<c:url var="deleteLink" value="/AdminProduct/delete">
		<c:param name="productID" value="${product.productID}" />
	</c:url>
	<tr>
		<td>${product.productID}</td>
 		<td>${product.productName}</td>
		<td>${product.productPrice}</td>
		<td>${product.productStock}</td>
		<td>${product.productDescription}</td>
<%-- 		<td><img src="${product.productImg}" alt="${product.productName}" width="200" /></td> --%>
		<td><fmt:formatDate pattern="yyyy-MM-dd" value="${product.productTimestamp}" /></td>
		<td>${product.productCategory}</td>
		<td>
			<a href="${updateLink}" class="btn btn-secondary">
				<i class="fas fa-angle-double-right"></i> 修改
			</a>
		</td>
		<td>
			<a href="${deleteLink}" class="btn btn-danger">
			<i class="fas fa-angle-double-right"></i> 刪除
			</a>
		</td>
	</tr>
	<c:if test="${not status.last}">
		<hr/>
	</c:if>
</c:forEach>

</body>
</html>