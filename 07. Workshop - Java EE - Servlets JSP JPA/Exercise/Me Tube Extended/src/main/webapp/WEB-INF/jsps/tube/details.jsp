<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.domain.models.view.tube.TubeDetailsViewModel" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<% TubeDetailsViewModel model = (TubeDetailsViewModel) request.getAttribute(WebConstants.ATTRIBUTE_MODEL); %>
<div class="container-fluid">
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEADER%>"/>
    <main>
        <hr class="my-2">
        <div class="container-fluid">
            <h2 class="text-center"><%=model.getTitle()%>
            </h2>
            <div class="row">
                <div class="col-md-6 my-5">
                    <div class="embed-responsive embed-responsive-16by9">
                        <iframe class="embed-responsive-item"
                                src="https://www.youtube.com/embed/<%=model.getYoutubeId()%>" allowfullscreen
                                frameborder="0"></iframe>
                    </div>
                </div>
                <div class="col-md-6 my-5">
                    <h1 class="text-center text-info"><%=model.getAuthor()%>
                    </h1>
                    <h3 class="text-center text-info"><%=model.getViews()%> Views</h3>
                    <div class="h5 my-5 text-center"><%=model.getDescription()%>
                    </div>
                </div>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_FOOTER%>"/>
</div>
</body>
</html>
