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
        <hr class="my-2"/>
        <div class="text-center mb-3">
            <h1>Register</h1>
        </div>
        <hr class="my-2">
        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="form-holder text-center">
                    <form class="form-inline" action="<%=WebConstants.URL_USER_REGISTER%>" method="POST">
                        <fieldset>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="username">Username</label>
                                <div class="controls">
                                    <input type="text" id="username" name="username" placeholder=""
                                           class="input-xlarge">
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="password">Password</label>
                                <div class="controls">
                                    <input type="password" id="password" name="password" placeholder=""
                                           class="input-xlarge">
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="confirmPassword">Confirm Password</label>
                                <div class="controls">
                                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder=""
                                           class="input-xlarge">
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <label class="control-label h3 mb-2" for="email">Email</label>
                                <div class="controls">
                                    <input type="text" id="email" name="email" placeholder=""
                                           class="input-xlarge">
                                </div>
                            </div>
                            <br/>
                            <div class="control-group">
                                <div class="controls">
                                    <button class="btn btn-info">Register</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <hr class="my-3"/>
    </main>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_FOOTER%>"/>
</div>
</body>
</html>
