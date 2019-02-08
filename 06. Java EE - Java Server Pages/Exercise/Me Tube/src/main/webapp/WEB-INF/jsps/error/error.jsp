<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="../templates/head.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css" />">
</head>
<body class="error">
<img class="error" src="https://http.cat/<%=response.getStatus()%>" alt="Status Code Image">
</body>
</html>
