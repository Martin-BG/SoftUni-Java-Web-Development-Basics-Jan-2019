<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<div class="container-fluid">
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEADER%>"/>
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h1 display-3">Welcome, <%= request.getSession().getAttribute(WebConstants.ATTRIBUTE_USERNAME)%>
            </p>

        </div>
        <hr class="my-3"/>
    </main>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_FOOTER%>"/>
</div>
</body>
</html>
