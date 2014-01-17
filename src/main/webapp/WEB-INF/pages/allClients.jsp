<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta content="text/html;charset=UTF-8">
    <title>Wszyscy klienci</title>
</head>
<body>
    <h1>Lista klientów</h1>
    <j:forEach items="${clients}" varStatus="info">
        <li><j:out value="clients[${info.index}].firstName"/></li>
    </j:forEach>
</body>
</html>