<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div xmlns:c="http://java.sun.com/jsp/jstl/core"
     style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">

    <a href="all"/>All users</a>

    | &nbsp;

    <a href="add"/>open notes</a>

    | &nbsp;

    <a href="del"/>delete user</a>

    | &nbsp;

    <a c:if="${pageContext.request.remoteUser!= null}" href="/logout">Logout</a>

</div>
