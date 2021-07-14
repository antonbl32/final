<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Users page</title>
</head>
<body>
<h1>Users list</h1>
<br>
<jsp:include page="menu.jsp" flush="true"/>
<div>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Username</th>
            <th>Password</th>
            <th>Open notes</th>
            <th>Delete user</th>
        </tr>
        <c:forEach  items="${users}" var ="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td><a href="<c:url value="${user.id}"/>">open notes</a></td>
                <td><form id="form1" action="/${user.id}" method="post">
                <a href="javascript:" onclick="document.getElementById('form1').submit();">Delete user</a>
                </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>