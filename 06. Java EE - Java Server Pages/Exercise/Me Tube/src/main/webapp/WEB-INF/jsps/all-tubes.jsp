<%@ page import="metube.domain.models.view.TubeNameViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% List<TubeNameViewModel> tubes = (List<TubeNameViewModel>) request.getAttribute("tubes"); %>
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
                    <p>No Tubes - <a href="<c:url value="/tubes/create"/>">Create Some</a>!</p>
                    <%} else {%>
                    <ul>
                        <% for (TubeNameViewModel tube : tubes) {%>
                        <li>
                            <c:url value="/tubes/details" var="tubeUrl">
                                <c:param name="name" value="<%=tube.getName()%>"/>
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
