<%--
  Created by IntelliJ IDEA.
  User: Mr Loc
  Date: 12/4/2024
  Time: 4:11 PM
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
    <h1>User Management</h1>
    <h2>
        <a href="waiters?action=waiters">List All Waiters</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Waiters
                </h2>
            </caption>
            <c:if test="${user != null}">
                <input type="hidden" name="id" value="<c:out value='${waiter.id}' />"/>
            </c:if>


            <tr>
                <th>User Name:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${waiter.name}' />"
                    />
                </td>
            </tr>



            <tr>
                <th>Waiters Email:</th>
                <td>
                    <input type="text" name="salary" size="45"
                           value="<c:out value='${waiter.salary}' />"
                    />
                </td>
            </tr>



            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>