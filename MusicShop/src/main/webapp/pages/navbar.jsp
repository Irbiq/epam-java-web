<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="background-color: rgba(25,25,25,0.94) !important;">
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/"><fmt:message key="navbar.home"/></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/pages/audios.jsp"><fmt:message key="page.audios"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/pages/albums.jsp"><fmt:message key="page.albums"/></a>
            </li>
            <c:if test="${user.role == \"ADMIN\" }">
            <li class="nav-item">
                <a class="nav-link" href="/pages/admin/users.jsp"><fmt:message key="page.users"/></a>
            </li>
            </c:if>
        </ul>
        <form class="form-inline">
            <ul class="nav navbar-nav navbar-right  my-2 my-lg-0">
                <li class="nav-item active">
                    <div class="dropdown">
                        <button class="nav-link btn btn-outline-info my-2 my-sm-0 btn-secondary dropdown-toggle"
                                type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            ${locale.getDisplayLanguage()}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="/controller?command=locale&lang=rus">русский</a>
                            <a class="dropdown-item" href="/controller?command=locale&lang=eng">english</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <%--<div class="btn btn-outline-info my-2 my-sm-0">--%>
                        <a  class="nav-link btn btn-outline-info my-2 my-sm-0" href="/pages/cart.jsp">
                            <img src="/images/shopping-cart-512.png" width="18" height="12">
                            <span class="badge badge-default badge-pill">${cart_list.size()}</span>
                        </a>
                    <%--</div>--%>
                </li>
                <c:if test="${user==null}">
                    <li class="nav-item active">
                        <a class="nav-link btn btn-outline-info my-2 my-sm-0" href="/pages/login.jsp"><fmt:message
                                key="navbar.login"/></a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link btn btn-outline-info my-2 my-sm-0"
                           href="/pages/registration.jsp"><fmt:message key="navbar.registration"/></a>
                    </li>
                </c:if>
                <c:if test="${user!=null}">
                    <li class="nav-item active">
                        <a class="nav-link  btn btn-outline-info my-2 my-sm-0"
                           href="/controller?command=logout"><fmt:message key="navbar.logout"/></a>
                    </li>
                </c:if>
            </ul>
        </form>
    </div>
</nav>

