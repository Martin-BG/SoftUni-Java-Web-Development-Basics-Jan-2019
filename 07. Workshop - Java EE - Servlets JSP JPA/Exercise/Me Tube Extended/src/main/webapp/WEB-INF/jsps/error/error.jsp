<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body class="error">
<h1><%=request.getAttribute("javax.servlet.error.status_code")%>
    - <%=request.getAttribute("javax.servlet.error.message")%>
</h1>
<h3>Request URI: <%=request.getAttribute("javax.servlet.error.request_uri")%>
</h3>
<% if (request.getAttribute("javax.servlet.error.exception_type") != null) {%>
<h3>Exception: <%=request.getAttribute("javax.servlet.error.exception_type") %>
</h3>
<% }%>
<img class="error" src="https://http.cat/<%=response.getStatus()%>" alt="Status Code Image">
</body>
</html>
