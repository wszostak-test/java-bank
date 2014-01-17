<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="j" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta content="text/html;charset=UTF-8">
    <title>Dodaj klienta</title>
</head>
<body>
    <h1>Dodaj klienta</h1>
    <f:form modelAttribute="client" method="POST">
        <table>
            <tr>
                <td>Imię:</td>
                <td><f:input path="firstName"/></td>
                <td><f:errors path="firstName"/></td>
            </tr>
            <tr>
                <td>Nazwisko:</td>
                <td><f:input path="lastName"/></td>
                <td><f:errors path="lastName"/></td>
            </tr>
            <j:forEach items="${client.addresses}" varStatus="info">
                <tr>
                    <td colspan="3">Adres ${info.index + 1}</td>
                </tr>
                <tr>
                    <td>Ulica:</td>
                    <td><f:input path="addresses[${info.index}].line1"/></td>
                    <td><f:errors path="addresses[${info.index}].line1"/></td>
                </tr>
                <tr>
                    <td>Miasto:</td>
                    <td><f:input path="addresses[${info.index}].city"/></td>
                    <td><f:errors path="addresses[${info.index}].city"/></td>
                </tr>
                <tr>
                    <td>Kraj:</td>
                    <td><f:input path="addresses[${info.index}].country"/></td>
                    <td><f:errors path="addresses[${info.index}].country"/></td>
                </tr>
            </j:forEach>
        </table>
        <input type="submit" value="Zapisz"/>
    </f:form>
</body>
</html>