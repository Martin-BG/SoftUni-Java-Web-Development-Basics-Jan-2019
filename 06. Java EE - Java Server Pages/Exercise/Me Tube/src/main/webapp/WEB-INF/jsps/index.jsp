<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>Welcome to MeTube!</h1>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>Cool app in beta version</h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-around">
                    <a class="btn btn-primary" href="<c:url value="<%=WebConstants.URL_TUBES_CREATE%>"/>">Create
                        Tube</a>
                    <a class="btn btn-primary" href="<c:url value="<%=WebConstants.URL_TUBES_ALL%>"/>">All Tubes</a>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <c:import url="templates/footer.jsp"/>
    </footer>
</div>
</body>
</html>
