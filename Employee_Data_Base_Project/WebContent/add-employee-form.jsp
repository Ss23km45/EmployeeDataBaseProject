<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>add employee</title>
<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
</head>
<body>
<div id="wrapper">
<div id="header">

<h2> Oracle Company Employees</h2>
</div>
</div>

<div id="container" >
<form action="EmployeeServletController" method="get">

<input type="hidden" name="something" value="add">

<table>
<tr>
<td>First Name : </td>
<td><input type="text" name="firstName">
</tr>

<tr>
<td>Last Name : </td>
<td><input type="text" name="lastName">
</tr>

<tr>
<td>Email : </td>
<td><input type="text" name="email">
</tr>


<tr>
<td>Salary : </td>
<td><input type="text" name="salary">
</tr>

<tr>
<td> <input type="submit" value="Add" name="addEmp">
</tr>


</table>


<a href="EmployeeServletController">Go back to list</a>

</form>

</div>
</body>
</html>