<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.domain.models.view.TubeNameViewModel" %>
<%@ page import="metube.web.WebConstants" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<% List<TubeNameViewModel> tubes = (List<TubeNameViewModel>) request.getAttribute(WebConstants.ATTRIBUTE_TUBES); %>
<div class="container">
    <main>
        <div class="jumbotron">
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h1>
                        All Tubes
                    </h1>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <h3>
                        Check our tubes below.
                    </h3>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col col-md-12 d-flex justify-content-center">
                    <% if (tubes.isEmpty()) { %>
                    <p>No Tubes - <a href="<c:url value="<%=WebConstants.URL_TUBES_CREATE%>"/>">Create Some</a>!</p>
                    <%} else {%>
                    <ul>
                        <% for (TubeNameViewModel tube : tubes) {%>
                        <li>
                            <c:url value="<%=WebConstants.URL_TUBES_DETAILS%>" var="tubeUrl">
                                <c:param name="<%=WebConstants.ATTRIBUTE_NAME%>" value="<%=tube.getName()%>"/>
                            </c:url>
                            <a href="<c:out value="${tubeUrl}"/>"><%=tube.getName()%>
                            </a>
                        </li>
                        <% }%>
                    </ul>
                    <%}%>
                </div>
            </div>
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
