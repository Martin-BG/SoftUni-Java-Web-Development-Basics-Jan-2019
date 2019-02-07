<%@ page import="metube.domain.models.view.TubeViewModel" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% TubeViewModel model = (TubeViewModel) request.getAttribute("model"); %>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>
                        <%= model.getName() %>
                    </h1>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>
                        <%= model.getDescription() %>
                    </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col col-md-6 d-flex justify-content-center">
                    <a href="<%= model.getYouTubeLink() %>">Link to video.</a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <p>
                        <%= model.getUploader() %>
                    </p>
                </div>
            </div>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="<c:url value="/"/>">Back to Home.</a>
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
