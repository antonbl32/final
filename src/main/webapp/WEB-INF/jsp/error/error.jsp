<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
</head>
<body>
<h4>BAD REQUEST: ${errMsg}</h4>
<h2 c:if=${errEx!=null}>${errEx.getMessage()}</h2>
</body>
</html>