<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="error.jsp" isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/css/bootstrap-grid.css">
    <link rel="stylesheet" href="/css/bootstrap-reboot.css">
    <link rel="stylesheet" href="/css/background.css">

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
<jsp:include page="navbar.jsp"></jsp:include>

<div class="container-fluid text-center" style="width: 80%">
    <c:if test="${user.role == \"ADMIN\" }">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAudio"
                data-whatever="@mdo"><fmt:message key="page.add_song"/>
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addAudioToAlbum"
                data-whatever="@mdo"><fmt:message key="page.add_to_album"/>
        </button>
        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#deleteAudio"
                data-whatever="@fat"><fmt:message key="page.delete"/>
        </button>
    </c:if>

    <ul class="list-group" style="list-style-type: none ; margin-top: 5px">
        <c:set scope="page" var="i" value="0"/>
        <c:forEach var="singleItem" items="${audios}" varStatus="status">
            <li style="margin-top: 1%">
                <div class="row text-center" style="width: 80%; margin-left: 10%">
                    <c:forEach var="audio" items="${audios}" begin="${i}" end="${i+3}" varStatus="status">
                        <div class="col">
                            <div class="card" style="width: 20rem;">
                                <img class="card-img-top" src="/images/covers/${audio.imageUrl}" alt="Card image cap">
                                <div class="card-block" style="margin-left: 10px">
                                    <h4 class="card-title"
                                        style="font-weight: bold; margin-bottom:1px ;margin-top: 3px">${audio.artist}</h4>
                                    <h6 class="card-text" style="margin-top: 4px">${audio.title}</h6>
                                </div>
                                <div class="card-block" style="margin-top: 5px ; text-align: right">
                                    <div>
                                        <div>
                                            <form name="price-form"
                                                  action="/controller?command=add_to_cart" method="post">
                                                    <%--<input name="album-id" type="hidden" value="${album.id}">--%>
                                                <input name="audio-buy" type="hidden" value="${audio.id}">
                                                <button class="btn btn-link" type="submit">${audio.price}$
                                                </button>
                                            </form>
                                        </div>
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

    <div class="modal fade" id="addAudio" tabindex="-1" role="dialog" aria-labelledby="addAudioLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addAudioLabel">New message</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="add-audio-id" action="/controller?command=add_audio" method="post">
                        <div class="form-group">
                            <label for="audio-title-id" class="form-control-label"><fmt:message
                                    key="page.title"/></label>
                            <input required="required" type="text" name="audio-title" class="form-control"
                                   id="audio-title-id">
                        </div>
                        <div class="form-group">
                            <label for="audio-artist-id" class="form-control-label"><fmt:message
                                    key="page.artist"/></label>
                            <select name="audio-artist" id="audio-artist-id" class="form-control">
                                <c:forEach var="artist" items="${artists}" varStatus="status">
                                    <option value="${artist.id}">
                                        <c:out value="${artist.name}"></c:out>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="audio-image-id" class="form-control-label">Image url</label>
                            <input type="text" name="audio-image" class="form-control" id="audio-image-id">
                        </div>
                        <div class="form-group">
                            <label for="audio-price-id" class="form-control-label"><fmt:message
                                    key="page.price"/></label>
                            <input type="text" required="required" name="audio-price" class="form-control"
                                   id="audio-price-id">
                        </div>

                        <div class="form-group">
                            <label for="audio-discount-id" class="form-control-label"><fmt:message
                                    key="page.discount"/></label>
                            <input type="text" name="audio-discount" class="form-control" id="audio-discount-id">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="page.cancel"/></button>
                    <%--id="submit-id" --%>
                    <button form="add-audio-id" id="" type="submit" class="btn btn-primary"><fmt:message key="page.ok"/>
                    </button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="addAudioToAlbum" tabindex="-1" role="dialog" aria-labelledby="addAudioLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addAudioToAlbumLabel"><fmt:message
                            key="page.add_to_album"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="add-audio-to-album-id" action="/controller?command=add_audio_to_album" method="post">
                        <div class="form-group">
                            <label for="audio-title-id" class="form-control-label"><fmt:message
                                    key="page.title"/></label>
                            <input required="required" type="text" name="audio-title" class="form-control"
                                   id="audio-to-album-title-id">
                        </div>
                        <div class="form-group">
                            <label for="audio-album-id" class="form-control-label"><fmt:message
                                    key="page.album"/></label>
                            <select name="audio-album" id="audio-album-id" class="form-control">
                                <c:forEach var="album" items="${albums}" varStatus="status">
                                    <option value="${album.id}">
                                        <c:out value="${album.title}"></c:out>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="audio-alb-image-id" class="form-control-label">Image url</label>
                            <input type="text" name="audio-image" class="form-control" id="audio-alb-image-id">
                        </div>
                        <div class="form-group">
                            <label for="audio-alb-price-id" class="form-control-label"><fmt:message
                                    key="page.price"/></label>
                            <input type="text" required="required" name="audio-price" class="form-control"
                                   id="audio-alb-price-id">
                        </div>

                        <div class="form-group">
                            <label for="audio-alb-discount-id" class="form-control-label"><fmt:message
                                    key="page.discount"/></label>
                            <input type="text" name="audio-discount" class="form-control" id="audio-alb-discount-id">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="page.cancel"/></button>
                    <button form="add-audio-to-album-id" type="submit" class="btn btn-primary"><fmt:message
                            key="page.ok"/>
                    </button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="deleteAudio" tabindex="-1" role="dialog" aria-labelledby="addAudioLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="deleteAudioLabel"><fmt:message
                            key="page.delete_audio"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="delete-audio-form-id" action="/controller?command=delete_audio" method="post">
                        <div class="form-group">
                            <label for="delete-audio-id" class="form-control-label">Artist</label>
                            <select name="audio-delete" id="delete-audio-id" class="form-control">
                                <c:forEach var="audio" items="${audios}" varStatus="status">
                                    <option value="${audio.id}">
                                        <c:out value="${audio.title}"></c:out>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="reset" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="page.cancel"/></button>
                    <button form="delete-audio-form-id" type="submit" id="delete-album-btn-id" class="btn btn-primary">
                        <fmt:message key="page.ok"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('#delete-album-btn-id').click(function () {
        console.log("CLICKED");
        $('#deletedAudio').modal('hide');
        $("#delete-audio-form-id")[0].submit();
        $("#delete-audio-form-id")[0].reset();
        return false;
    });
    $('#add-audio-btn-id').click(function () {
        console.log("CLICKED");
        $('#addAudio').modal('hide');
        if ($("#audio-title-id") != null || $("#audio-title-id") != "") {
            $("#add-audio-id")[0].submit();
        }
        $("#add-album-form-id")[0].reset();
        return false;
    });
</script>
</body>
</html>
