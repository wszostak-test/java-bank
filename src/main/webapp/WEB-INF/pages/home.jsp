<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta content="text/html;charset=UTF-8">
    <title><c:message code="homeTitle"/></title>
</head>
<body>
    <h1><c:message code="homeTitle"/></h1>
    <ul>
        <li><a href="addClientForm.html"><c:message code="addClientLink"/></a></li>
        <li><a href=""><c:message code="showClientsLink"/></a></li>
    </ul>
</body>
</html>