<%@ page import="org.n0rth.springlessonsecurity.entity.UserRole" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Info Page</title>
</head>
<body>
<h1>Hello, Employee!</h1>
<br><br>


<security:authorize access="hasRole('HR')">
    <input type="button" value="Salary"
           onclick="window.location.href='hr_info'"> HR INFO
</security:authorize>
<security:authorize access="hasRole('MANAGER')">
    <input type="button" value="Performance"
           onclick="window.location.href='manager_info'"> MANAGER INFO
</security:authorize>
</body>
</html>
