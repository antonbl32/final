<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>
        Add Note
    </title>
</head>
<body>
<jsp:include page="menu.jsp" flush="true"/>
<br>
<%--@elvariable id="note" type="it.free.final_spring.entity.NoteEntity"--%>
<form:form action="/noteadd" modelAttribute="note" method="post" >
    Note text <form:input path="name"/>
    <form:errors path="name"/>
    <br><br>
    <input type="submit" value="Add"/>
</form:form>
</body>
</html>