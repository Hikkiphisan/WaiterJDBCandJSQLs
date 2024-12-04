<%--
  Created by IntelliJ IDEA.
  User: Mr Loc
  Date: 12/4/2024
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>waiters Management</h1>
    <h2>
        <a href="/waiters?action=create">Add New waiters</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of waiters</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Waiter</th>
        </tr>
        <c:forEach var="waiter" items="${listWaiters}">
            <tr>
                <td><c:out value="${waiter.id}"/></td>
                <td><c:out value="${waiter.name}"/></td>
                <td><c:out value="${waiter.salary}"/></td>
<%--                <td><c:out value="${waiter.country}"/></td>--%>
                <td>
                    <a href="/waiters?action=edit&id=${waiter.id}">Edit</a>
                    <a href="/waiters?action=delete&id=${waiter.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>