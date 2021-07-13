<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <title>
        Add User
    </title>
</head>

<body>
<jsp:include page="menu.jsp" flush="true"/>
<div class="container">
    <h1>This is secured!</h1>
    <p>
        Hello <b><c:out value="${pageContext.request.remoteUser}"/></b>
    </p>
    <a c:if="${pageContext.request.remoteUser!= null}" href="/logout">Logout</a>
</div>
<br>
<%--@elvariable id="userDTO" type="it.free.final_spring.dto.UserDTO"--%>
<form:form action="/" modelAttribute="userDTO" method="post" >
    Username <form:input path="username"/>
    <form:errors path="username"/>
    <br><br>
    Password <form:input path="password"/>
    <form:errors path="password"/>
    <br><br>
    <input type="submit" value="Add"/>

</form:form>
</body>


</html>