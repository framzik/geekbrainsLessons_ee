<%@ page contentType="text/html;charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/main">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/catalog">Каталог</a>
                </li>
                <c:url value="/product/" var="productUrl"/>
                <li class="nav-item">
                    <a class="nav-link" href="${productUrl}">Товары</a>
                </li>
                <c:url value="/user/" var="userUrl"/>
                <li class="nav-item">
                    <a class="nav-link" href="${userUrl}">Пользователи</a>
                </li>
                <c:url value="/category/" var="categoryUrl"/>
                <li class="nav-item">
                    <a class="nav-link" href="${categoryUrl}">Категории</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cart">Корзина</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/order">Оформление заказа</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/company">Компания</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
