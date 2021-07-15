<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>

<head>
    <title>
        Welcome page
    </title>
</head>

<body>
<jsp:include page="menu.jsp" flush="true"/>
<h4>Good day ${username}</h4>
</body>


</html>