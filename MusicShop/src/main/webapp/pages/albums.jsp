<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap-grid.css">
    <link rel="stylesheet" href="/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="/css/background.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
            integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"
            integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ"
            crossorigin="anonymous"></script>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

</head>
<body style="background-color: #222222">
<jsp:include page="navbar.jsp"/>

<div class="container-fluid text-center" style="width: 80%">

    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAlbum" data-whatever="@mdo">
        <fmt:message key="page.add_album"/>
    </button>
    <button type="button" class="btn btn-success" data-toggle="modal" data-target="#deleteAlbum" data-whatever="@fat">
        <fmt:message key="page.delete"/>
    </button>


    <ul class="list-group" style="list-style-type: none ; margin-top: 5px">
        <c:set scope="page" var="i" value="0"/>
        <c:forEach var="singleItem" items="${albums}" varStatus="status">
            <li style="margin-top: 1%">
                <div class="row text-center" style="width: 80%; margin-left: 10%">
                    <c:forEach var="album" items="${albums}" begin="${i}" end="${i+3}" varStatus="status">
                        <div class="col">
                            <div class="card" style="width: 20rem; ">
                                <img class="card-img-top" src="/images/covers/${album.imageUrl}" alt="Card image cap">
                                <div class="card-block" style="margin-left: 10px">
                                    <h4 class="card-title"
                                        style="font-weight: bold; margin-bottom:1px ;margin-top: 3px">
                                            ${album.artist}</h4>
                                    <h6 class="card-text" style="margin-top: 4px">${album.title}</h6>
                                </div>
                                <div class="card-block" style="margin-top: 5px ; text-align: right">
                                    <div>
                                            <%--<form name="price-form" action="/controller?command=register" method="post">--%>
                                        <span style="text-align: right; margin-right: 10px"> <a
                                                href="/controller?command=get_album&id=${album.id}"
                                                class="card-link">${album.id}</a></span>
                                            <%--</form>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <c:set var="i" value="${i+4}"/>
            </li>
        </c:forEach>
    </ul>
</div>


<div class="modal fade" id="addAlbum" tabindex="-1" role="dialog" aria-labelledby="addAlbumLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addAlbumLabel"><fmt:message key="page.add_album"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="add-album-form-id" action="/controller?command=add_album" method="post">
                    <div class="form-group">
                        <label for="album-title-id" class="form-control-label"><fmt:message key="page.title"/></label>
                        <input type="text" name="album-title" class="form-control" id="album-title-id">
                    </div>
                    <div class="form-group">
                        <label for="album-image-id" class="form-control-label">Image url</label>
                        <input type="text" name="album-image" class="form-control" id="album-image-id">
                    </div>
                    <div class="form-group">
                        <label for="album-artist-id" class="form-control-label"><fmt:message key="page.artist"/></label>
                        <select required name="album-artist" id="album-artist-id" class="form-control">
                            <c:forEach var="artist" items="${artists}" varStatus="status">
                                <option value="${artist.id}">
                                    <c:out value="${artist.name}"></c:out>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                        key="page.cancel"/></button>
                <button id="add-album-btn-id" form="add-album-form-id" type="submit"
                        <%--id="submit-id" --%>class="btn btn-primary"><fmt:message key="page.ok"/>
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="deleteAlbum" tabindex="-1" role="dialog" aria-labelledby="deleteAlbum"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteAlbumLabel"><fmt:message key="page.delete_album"/></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="delete-album-form-id" action="/controller?command=delete_album" method="post">
                    <div class="form-group">
                        <label for="album-id" class="form-control-label">Album</label>
                        <select name="delete-album" id="album-id" class="form-control">
                            <c:forEach var="album" items="${albums}" varStatus="status">
                                <option value="${album.id}">
                                    <c:out value="${album.title}"></c:out>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="reset" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                        key="page.cancel"/></button>
                <button id="delete-album-btn-id" form="delete-album-form-id" type="submit"
                        <%--id="submit-id" --%>class="btn btn-primary"><fmt:message key="page.ok"/></button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#delete-album-btn-id').click(function () {
        console.log("CLICKED");
        $('#deletedAlbum').modal('hide');
        $("#delete-album-form-id")[0].submit();
        $("#delete-album-form-id")[0].reset();
        return false;
    });
    $('#add-album-btn-id').click(function () {
        console.log("CLICKED");
        $('#addAlbum').modal('hide');
        if ($("#album-title-id") != null || $("#album-title-id") != "" ) {
            $("#add-album-form-id")[0].submit();
        }
        $("#add-album-form-id")[0].reset();
        return false;
    });
</script>

</body>
</html>
