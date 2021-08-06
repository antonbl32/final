<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${user.username} notes</title>
</head>
<body>
<h2>User ${user.username} has notes:</h2>
<jsp:include page="menu.jsp" flush="true"/>
<div>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Title note</th>
        </tr>
        <c:forEach  items="${user.notes}" var ="note">
            <tr>
                <td>${note.id}</td>
                <td>${note.name}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>