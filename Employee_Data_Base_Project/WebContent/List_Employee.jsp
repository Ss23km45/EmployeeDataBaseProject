<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*,com.coding.ninja.jsp.webapp.server.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Data of Oracle</title>
<link type="text/css" rel="stylesheet" href="css/style.css"></link>
</head>
<body>
<%
List<Employee> empList = (List<Employee>) request.getAttribute("EMPLIST");
%>

<!--  Lets Create Some table to display this informations. -->

<div id="wrapper">
<div id="header">

<h2> Oracle Company Employees</h2>

</div>

</div>
<div id="container">

<table>
<tr>
<th>Employee ID</th>
<th>Employee First Name</th>
<th>Employee Last Name</th>
<th>Employee Email</th>
<th>Employee Salary</th>
</tr>
 <% for(Employee emp : empList) { %>
		<tr>
		<td><%=emp.getEmpId() %>
		<td><%=emp.getEmpFname() %></td>
		<td><%=emp.getEmpLname() %></td>
		<td><%=emp.getEmpEmail() %></td>
		<td><%=emp.getEmpSal() %></td>
		</tr>
<% } %>

</table>

</div>
</body>
</html>