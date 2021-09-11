<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Compra Ae!</title>

        <%@ include file="/layout/estilos.jsp" %>
    </head>
    <body>

        <main>
            <%@ include file="/layout/navbar.jsp" %>
        </main>

        <div class="container">
            <h1 class="text-center mt-5">Compra Ae! - Ecommerce</h1>

            <div class="row mt-5">
                <c:forEach items="${items}" var="item">
                    <div class="col-3">
                        <div class="card">
                            <img
                                style="height: 200px;"
                                src="<c:out value="${item.image}" />"
                                class="card-img-top rounded img-fluid"
                                alt="<c:out value="${item.name}" />"
                            />
                            <div class="card-body">
                                <h5 class="card-title">
                                    <c:out value="${item.name}" />

                                    <small class="float-end text-muted h6">
                                        <c:out value="${item.getFormatedPrice()}" />
                                    </small>
                                </h5>
                                <p class="card-text">
                                    <c:out value="${item.description}" />
                                </p>
                                <div class="d-grid gap-2">
                                    <a
                                        href="cart?action=add&id=<c:out value="${item.id}" />"
                                        class="btn btn-sm btn-outline-success"
                                    >
                                        <i class="bi bi-cart-plus-fill"></i>
                                        Comprar
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <%@ include file="/layout/javascript.jsp" %>
    </body>
</html>
