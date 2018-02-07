<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

    <link href="../css/ctable.css" rel="stylesheet">
</head>
<jsp:useBean id="album" scope="request" type="main.com.bsu.musicshop.entity.Album"/>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container rounded" style=" border:1px solid #cecece; margin-top: 1%; padding: 0%">
    <div class="row" style="margin: 0%">
        <div class="col-3" style="padding: 0%">
            <img src="/images/covers/${album.imageUrl}" width="200 px" height="200px">
        </div>
        <div class="col-9">
            <div class="row cst-text"
                 style="color:rgb(247, 255, 251);  font-size: 20px; font-weight: 300;line-height: 35px;margin-bottom: 1px;white-space: normal">
                ${album.artist}
            </div>
            <div class="row cst-text" style="color:rgb(247, 255, 251); font-size: 35px;">
                ${album.title}
            </div>
        </div>
    </div>
    <div>
        <table class="table" style="margin: 0%">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Artist</th>
                <th scope="col">Track</th>
                <th scope="col">Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${audios}" var="audio" varStatus="index">
                <tr>
                    <th>${index.index+1}</th>
                    <td>${audio.artist}</td>
                    <td>${audio.title}</td>
                    <td>
                        <button class="btn btn-primary">${audio.price}</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div style="background: rgba(25,21,8,0.7);margin-left: 1em ; padding-top: 2%">
        <h4 style="color: rgb(247, 255, 251); border-bottom: solid  rgb(247, 255, 251);">Comments</h4>
        <div class="row" style="margin: 0%">
            <form class="form-inline" method="post" action="/controller?command=add_comment">
                <div  style="padding: 0%; border-bottom: solid #007bff;">
                    <textarea name="comment-text" id="textarea"
                              style="border-bottom: solid rgba(22,25,23,0.7) ; height: 35px; color: rgb(247, 255, 251); background-color: #222222; border: none;"
                              class="style-scope iron-autogrow-textarea" cols="100" autocomplete="off" required=""
                              maxlength="10000"></textarea>
                    <input type="hidden" name="comment-album-id" value="${album.id}"/>
                </div>
                <div >
                    <button class="btn btn-primary" type="submit" style="height: 35px; width: 60px">send</button>
                </div>
            </form>
        </div>
        <ul class="list-group" style="list-style-type: none ; margin-top: 5px">
            <c:set scope="page" var="i" value="1"/>
            <c:forEach var="singleItem" items="${albums}" begin="1" end="4" varStatus="status">
                <li style="margin-top: 1%">

                    <div class="row" style="margin: 0%">
                        <strong style="color: rgb(247, 255, 251);">Author</strong>
                    </div>
                    <div class="row" style="margin: 0%">
                        <p style="color: rgb(247, 255, 251);">TEXTTEXTTEXT</p>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
</html>
