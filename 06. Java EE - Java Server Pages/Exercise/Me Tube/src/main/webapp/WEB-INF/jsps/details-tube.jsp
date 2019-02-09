<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.domain.models.view.TubeViewModel" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<% TubeViewModel model = (TubeViewModel) request.getAttribute(WebConstants.ATTRIBUTE_MODEL); %>
<div class="container">
    <main>
        <div class="jumbotron">
            <% if (model == null) { %>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>
                        Tube not found!
                    </h1>
                </div>
            </div>
            <%} else {%>
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
                    <a target="_blank" href="<%= model.getYouTubeLink() %>">Link to video.</a>
                </div>
                <div class="col col-md-6 d-flex justify-content-center">
                    <p>
                        <%= model.getUploader() %>
                    </p>
                </div>
            </div>
            <%}%>
            <hr/>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <a href="<c:url value="<%=WebConstants.URL_HOME%>"/>">Back to Home.</a>
                </div>
            </div>
        </div>
    </main>
    <footer>
        <c:import url="<%=WebConstants.JSP_TEMPLATE_FOOTER%>"/>
    </footer>
</div>
</body>
</html>
