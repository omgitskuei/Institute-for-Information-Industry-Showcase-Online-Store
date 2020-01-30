<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h3>Form</h3>
<form action="<c:url value="/productcontroller"></c:url>" method="post" enctype="multipart/form-data">	
<!-- DIRECTS to /hello.controller via method POST -->
	<table>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="name"> </td> 
		    <td>${errors.name}</td>
		</tr> 
		<tr> 
			<td>Price:</td>
			<td><input type="text" name="Price"></td>
			<td>${errors.price}</td>
		</tr> 
		<tr>	
			<td>Stock:</td>
			<td><input type="text" name="Stock"></td>
			<td>${errors.stock}</td>
		</tr> 	
		<tr>	
			<td>Description:</td>
			<td><input type="text" name="Description"></td>
			<td>${errors.despription}</td>
		</tr>
		<tr>	
			<td>Image:</td>
			<td><input  type="file" name="Image">
		    <input type="submit" value="Upload" /></td>
			<td>${errors.img}</td>
		</tr> 	
		<tr>	
			<td>Timestamp:</td>
			<td><input type="text" name="Timestamp"></td>
			<td>${errors.timestamp}</td>
		</tr> 
		<tr>	
			<td>Category:</td>
			<td><input type="text" name="Category"></td>
			<td>${errors.category}</td>
		</tr>
		<tr>
			<td><input type="submit" ></td>
		</tr>
	</table>
</form>