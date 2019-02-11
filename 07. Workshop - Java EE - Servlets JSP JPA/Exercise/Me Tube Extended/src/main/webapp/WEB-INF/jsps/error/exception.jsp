<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<h1>Opps... :)</h1>
<table width="100%" border="1">
    <tr valign="top">
        <td width="150px"><b>Error:</b></td>
        <td><c:out value="${pageContext.exception}"/></td>
    </tr>
    <tr valign="top">
        <td><b>URI:</b></td>
        <td><c:out value="${pageContext.errorData.requestURI}"/></td>
    </tr>
    <tr valign="top">
        <td><b>Status code:</b></td>
        <td>${pageContext.errorData.statusCode}</td>
    </tr>
    <tr valign="top">
        <td><b>Stack trace:</b></td>
        <td>
            <c:forEach var="trace"
                       items="${pageContext.exception.stackTrace}">
                <p><c:out value="${trace}"/></p>
            </c:forEach>
        </td>
    </tr>
</table>
</body>
</html>
