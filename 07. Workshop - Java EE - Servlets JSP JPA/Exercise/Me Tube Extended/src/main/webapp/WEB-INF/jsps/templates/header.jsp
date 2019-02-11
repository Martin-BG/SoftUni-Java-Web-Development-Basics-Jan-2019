<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.web.WebConstants" %>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-color-dark">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand h4" href="<%=WebConstants.URL_INDEX%>">MeTube&trade;</a>

        <div class="collapse navbar-collapse justify-content-end row" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <% if (request.getSession().getAttribute(WebConstants.ATTRIBUTE_USERNAME) == null) { %>
                <li class="nav-item active col-md-4">
                    <a class="nav-link h5" href="<%=WebConstants.URL_INDEX%>">Home</a>
                </li>
                <li class="nav-item active col-md-4">
                    <a class="nav-link h5" href="<%=WebConstants.URL_USER_LOGIN%>">Login</a>
                </li>
                <li class="nav-item active col-md-4">
                    <a class="nav-link h5" href="<%=WebConstants.URL_USER_REGISTER%>">Register</a>
                </li>
                <% } else {%>
                <li class="nav-item active col-md-3">
                    <a class="nav-link h5" href="<%=WebConstants.URL_USER_HOME%>">Home</a>
                </li>
                <li class="nav-item active col-md-3">
                    <a class="nav-link h5" href="<%=WebConstants.URL_USER_PROFILE%>">Profile</a>
                </li>
                <li class="nav-item active col-md-3">
                    <a class="nav-link h5" href="<%=WebConstants.URL_TUBE_UPLOAD%>">Upload</a>
                </li>
                <li class="nav-item active col-md-3">
                    <a class="nav-link h5" href="<%=WebConstants.URL_USER_LOGOUT%>">Logout</a>
                </li>
                <% } %>
            </ul>
        </div>
    </nav>
</header>