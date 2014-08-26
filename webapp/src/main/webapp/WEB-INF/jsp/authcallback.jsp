<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
</head>
<body role="document">
    Authentication successful.
    <ul>
        <li>Source: ${user.source}</li>
        <li>User ID: ${user.id}</li>
    </ul>
</body>
</html>
