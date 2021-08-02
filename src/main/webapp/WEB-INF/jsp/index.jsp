<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <title>
        Hello page
    </title>
</head>

<body>
<jsp:include page="menu.jsp" flush="true"/>
<h3>Hello ${auth}</h3>
</body>


</html>