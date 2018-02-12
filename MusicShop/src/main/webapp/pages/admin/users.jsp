<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
            integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
            crossorigin="anonymous"></script>

    <link href="../../css/ctable.css" rel="stylesheet">
</head>
<jsp:useBean id="users" scope="session" type="java.util.List"/>
<body>
<jsp:include page="../navbar.jsp"></jsp:include>
<div class="container-fluid" >
    <table class="table">
        <thead class="thead-inverse">
        <tr>
            <th>#</th>
            <th>id</th>
            <th><fmt:message key="page.login"/></th>
            <th><fmt:message key="page.password"/></th>
            <th><fmt:message key="page.name"/></th>
            <th><fmt:message key="page.surname"/></th>
            <th><fmt:message key="page.role"/></th>
            <th><fmt:message key="page.edit"/></th>
            <th><fmt:message key="page.delete"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}" varStatus="index">
            <tr>
                <td>${index.index+1}</td>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.surname}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td>
                    <form method="POST" action="/controller?command=user_edit">
                        <input type="hidden" name="user-edit-id" value="${ user.id }"/>
                        <input type="submit" value="<fmt:message key="page.edit"/>" class="btn btn-primary btn-xs"/>
                    </form>
                </td>
                <td>
                    <form method="POST" action="/controller?command=user_delete">
                        <input type="hidden" name="user-delete-id" value="${ user.id }"/>
                        <input type="submit" value="<fmt:message key="page.delete"/>" class="btn btn-danger btn-xs"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
