<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.domain.models.view.user.UserProfileViewModel" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<% UserProfileViewModel model = (UserProfileViewModel) request.getAttribute(WebConstants.ATTRIBUTE_MODEL); %>
<div class="container-fluid">
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEADER%>"/>
    <main>
        <hr class="my-2"/>
        <div class="text-center mt-3">
            <h4 class="text-info text-center">@<%= model.getUsername()%>
            </h4>
            <h4 class="text-info text-center">(<%= model.getEmail()%>)</h4>
        </div>
        <hr>
        <div class="container-fluid">
            <div class="row d-flex flex-column">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col">Author</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (int i = 0; i < model.getTubes().size(); i++) { %>
                    <tr>
                        <td><%= i + 1%>
                        </td>
                        <td><%= model.getTubes().get(i).getTitle()%>
                        </td>
                        <td><%= model.getTubes().get(i).getAuthor()%>
                        </td>
                        <td>
                            <a href="<%=WebConstants.URL_TUBES_DETAILS%><%=model.getTubes().get(i).getId()%>">Details</a>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_FOOTER%>"/>
</div>
</body>
</html>
