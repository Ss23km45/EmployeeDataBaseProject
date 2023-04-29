<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Data of Oracle</title>
<link type="text/css" rel="stylesheet" href="css/style.css"></link>
</head>
<body>
<!--  Lets Create Some table to display this informations. -->

<div id="wrapper">
<div id="header">

<h2> Oracle Company Employees</h2><br/>
<br/>
</div>
<div id="container">
<input type="button" name="AddEmployee" value="AddEmployee" 
	onClick="window.location.href='add-employee-form.jsp';return false;" class="add-student">
</div><br/>
<table>
<tr>
<th>Employee First Name</th>
<th>Employee Last Name</th>
<th>Employee Email</th>
<th>Employee Salary</th>
<th>Action</th>
</tr>
		
 <c:forEach var="emp" items="${EMPLIST}">
 		<c:url var="tempLink" value="EmployeeServletController">
 			<c:param name="something" value="LOAD"/>
 			<c:param name="employeeId" value="${emp.empId}" />
 		</c:url>
 		<c:url var="delete" value="EmployeeServletController">
 			<c:param name="something" value="DELETE"/>
 			<c:param name="deleteId" value="${emp.empId}" />
 		</c:url>
		<tr>
		<td>${emp.empFname}</td>
		<td>${emp.empLname}</td>
		<td>${emp.empEmail}</td>
		<td>${emp.empSal}</td>
		<td><a href="${tempLink}">Update</a> | <a href="${delete}" onclick="if (!(confirm('Are you sure you want to delete this student?'))) return false"> Delete</a></td>
		</tr>
</c:forEach>

</table>

</div>
</body>
</html>