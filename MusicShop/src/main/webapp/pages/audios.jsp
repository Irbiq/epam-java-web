<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="container-fluid" style="width: 80%">
    <ul class="list-group" style="list-style-type: none ; margin-top: 5px">
        <c:set scope="page" var="i" value="1"></c:set>
        <c:forEach var="singleItem" items="${audios}" begin="1" end="${(audios_amount/4)+4}" varStatus="status">
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
                                        <form name="price-form" style="margin-bottom: 1rem"
                                              action="/controller?command=add_to_cart" method="get">
                                            <input type="hidden" name="comment-album-id" value="${album.id}"/>
                                            <button type="submit" class="btn btn-link">${audio.price}</button>
                                        </form>
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
    <form action="/pages/admin/add_audio.jsp" method="get">
        <button type="submit" class="btn btn-primary">Add Audio</button>
    </form>

</div>
</div>
</body>
</html>
