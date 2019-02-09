<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="metube.web.WebConstants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="<%=WebConstants.JSP_TEMPLATE_HEAD%>"/>
</head>
<body>
<div class="container">
    <main>
        <div class="jumbotron">
            <div align="center">
                <h1>Create Tube!</h1>
                <hr/>
                <form action="<c:url value="<%=WebConstants.URL_TUBES_CREATE%>"/>" method="post">
                    <div class="form-group">
                        <label>
                            <div>Title</div>
                            <input minlength="1" name="name" required type="text"/>
                        </label>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label>
                            <div>Description</div>
                            <textarea name="description" rows="5"></textarea>
                        </label>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label>
                            <div>YouTube Link</div>
                            <input minlength="1" name="youTubeLink" required type="text"/>
                        </label>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <label>
                            <div>Uploader</div>
                            <input minlength="1" name="uploader" required type="text"/>
                        </label>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <input class="btn btn-primary" type="submit" value="Create Tube"/>
                    </div>
                </form>
                <div class="mt-4"><a href="<c:url value="<%=WebConstants.URL_HOME%>"/>">Back to Home.</a></div>
            </div>
        </div>
    </main>
    <footer>
        <c:import url="<%=WebConstants.JSP_TEMPLATE_FOOTER%>"/>
    </footer>
</div>
</body>
</html>