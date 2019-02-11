<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body class="error">
<img class="error" src="https://http.cat/<%=response.getStatus()%>" alt="Status Code Image">
</body>
</html>
