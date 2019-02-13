<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.domain.models.view.tube.TubeThumbnailViewModel" %>
<%@ page import="metube.web.WebConstants" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<% List<TubeThumbnailViewModel> tubes = (List<TubeThumbnailViewModel>) request.getAttribute(WebConstants.ATTRIBUTE_MODEL); %>
<div class="container-fluid">
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEADER%>"/>
    <main>
        <hr class="my-3"/>
        <div class="jumbotron">
            <p class="h1 display-3">Welcome, <%= request.getSession().getAttribute(WebConstants.ATTRIBUTE_USERNAME)%>
            </p>

        </div>
        <hr class="my-3"/>

        <div class="container">
            <div class="row text-center">
                <% for (TubeThumbnailViewModel tube : tubes) {%>
                <div class="m-2 justify-content-center width-30p">
                    <div>
                        <a href="<%=WebConstants.URL_TUBE_DETAILS%>/<%=tube.getId()%>">
                            <img class="img-fluid img-thumbnail"
                                 src="https://img.youtube.com/vi/<%=tube.getYoutubeId()%>/0.jpg" alt="tube thumbnail">
                        </a>
                    </div>
                    <h5 class="text-center"><%=tube.getTitle()%>
                    </h5>
                    <p class="text-center font-italic"><%=tube.getAuthor()%>
                    </p>
                </div>
                <%}%>
            </div>
        </div>
    </main>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_FOOTER%>"/>
</div>
</body>
</html>
