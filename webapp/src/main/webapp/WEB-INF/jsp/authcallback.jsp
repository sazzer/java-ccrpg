<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8"/>
    <script type="text/javascript">
        opener.authenticationCallback({
            provider: "${user.source}",
            id: "${user.id}",
            newUser: ${newUser}
        });
        window.close();
    </script>
</head>
<body role="document">
    Authentication successful.
    <ul>
        <li>Source: ${user.source}</li>
        <li>User ID: ${user.id}</li>
        <li>New User: ${newUser}</li>
    </ul>
</body>
</html>
