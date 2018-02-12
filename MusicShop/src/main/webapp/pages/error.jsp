<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<%@ page isErrorPage = "true" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container">
    <h1>Opps...</h1>
    <p>Sorry, an error occurred.</p>
    <p>Here is the exception stack trace: </p>
    <pre>${exception}</pre><%--<% exception.printStackTrace(response.getWriter()); %>--%>
</div>
</body>
</html>

