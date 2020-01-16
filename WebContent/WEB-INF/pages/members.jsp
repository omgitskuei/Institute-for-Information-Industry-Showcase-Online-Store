<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>Welcome to Members World</h3>
<form:form method="POST" action="addMembers" modelAttribute="members">
	<table>
		<tr>
			<td><form:label path="memberName">MemberName:</form:label></td>
			<td><form:input path="memberName" /></td>
		</tr>
		<tr>
			<td><form:label path="gender">Gender:</form:label></td>
			<td><form:input path="gender" /></td>
		</tr>
		<tr>
			<td><form:label path="age">Age:</form:label></td>
			<td><form:input path="age" /></td>
		</tr>
		<tr>
			<td colspan="2"><form:button value="Send">Submit</form:button></td>
		</tr>
	</table>
</form:form>