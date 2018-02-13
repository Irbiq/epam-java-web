<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="pages/error.jsp" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Cover Template for Bootstrap</title>

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

    <link href="/css/index.css" rel="stylesheet">
</head>


<jsp:useBean id="audios" scope="session" type="java.util.List"/>
<jsp:useBean id="albums" scope="session" type="java.util.List"/>
<body>
<jsp:include page="/pages/navbar.jsp"></jsp:include>
<div class="jumbotron jumbotron-fluid" style="padding: 0% ;margin: 0%;">
    <%--<div class="container container-fluid">
        <img src="images/dashboard_header.f28f1fbea2f9.jpg" width="100%"height="100%">
    </div>--%>
</div>

<div class="container-fluid">
    <div style="text-align: left;">
        <h1 style="color: rgb(247, 255, 251); font-size: 5em;font-family: 'Arial Narrow' ;margin-left: 1%;">
            <fmt:message key="page.audios"/>
        </h1>
    </div>
    <div id="audio" class="carousel slide" data-ride="carousel" data-interval="false">
        <ol class="carousel-indicators">
            <%-- <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
             <li data-target="#carouselExampleIndicators" data-slide-to="1"></li> //ПЕРЕДЕЛАТЬ
             <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>--%>
        </ol>
        <div class="carousel-inner">
            <c:set var="t" scope="page" value="${0}"/>
            <c:forEach var="i" begin="0" end="${(sessionScope.audios.size())/5}"><%--${audios.size/5}--%>
            <c:choose>
            <c:when test="${t==0}">
            <div class="carousel-item active">
                </c:when>
                <c:otherwise>
                <div class="carousel-item">
                    </c:otherwise>
                    </c:choose>
                    <div class="row text-center" style="width: 80%; margin-left:10% ">
                        <c:forEach items="${audios}" var="audio" begin="${t}" end="${t+4}" varStatus="status">
                            <div class="col">
                                <div class="card" style="width: 20rem">
                                    <img class="card-img-top" src="/images/covers/${audio.imageUrl}"
                                         alt="Card image cap">
                                    <div class="card-block" style="margin-left: 10px">
                                        <h4 class="card-title"
                                            style="font-weight: bold; margin-bottom:1px ;margin-top: 3px">
                                                ${audio.artist}
                                        </h4>
                                        <h6 class="card-text" style="margin-top: 4px">${audio.title}</h6>
                                    </div>
                                    <div class="card-block" style="margin-top: 5px ; text-align: right">
                                        <div>
                                            <form  name="price-form"
                                                  action="/controller?command=add_to_cart" method="post">
                                                <input name="audio-buy" type="hidden" value="${audio.id}">
                                                <button class="btn btn-link" type="submit">${audio.price}$
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:set var="t" value="${t+5}"/>
                    </div>
                </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#audio" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#audio" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>

    <div>
        <h1 style="color: rgb(247, 255, 251); text-align: left; font-size: 5em;font-family: 'Arial Narrow' ;margin-left: 1%">
            <fmt:message key="page.albums"/></h1>
    </div>

    <div id="album" class="carousel slide" data-ride="carousel" data-interval="false">
        <ol class="carousel-indicators">
        </ol>
        <div class="carousel-inner">
            <c:set var="t" scope="page" value="${0}"/>
            <c:forEach var="i" begin="0" end="${(sessionScope.albums.size())/5-1}"><%--${audios.size/5}--%>
            <c:choose>
            <c:when test="${t==0}">
            <div class="carousel-item active">
                </c:when>
                <c:otherwise>
                <div class="carousel-item">
                    </c:otherwise>
                    </c:choose>
                    <div class="row text-center" style="width: 80%; margin-left:10% ">
                        <c:forEach items="${albums}" var="album" begin="${t}" end="${t+4}" varStatus="status">
                            <div class="col">
                                <div class="card" style="width: 20rem">
                                    <img class="card-img-top" src="/images/covers/${album.imageUrl}"
                                         alt="Card image cap">
                                    <div class="card-block" style="margin-left: 10px">
                                        <h4 class="card-title"
                                            style="font-weight: bold; margin-bottom:1px ;margin-top: 3px">
                                                ${album.artist}
                                        </h4>
                                        <h6 class="card-text" style="margin-top: 4px">${album.title}</h6>
                                    </div>
                                    <div class="card-block" style="margin-top: 5px ; text-align: right">
                                        <div>
                                            <form name="price-form"
                                                  action="/controller?command=get_album&id=${album.id}" method="post">
                                                <span style="text-align: right; margin-right: 10px"> <a
                                                        href="/controller?command=get_album&id=${album.id}"
                                                        class="card-link"><fmt:message key="page.open"/></a></span>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <c:set var="t" value="${t+5}"/>
                    </div>
                </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#album" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#album" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>

        </div>
    </div>
</div>
</body>
</html>
