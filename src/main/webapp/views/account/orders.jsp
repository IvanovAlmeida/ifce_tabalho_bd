<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Pedidos - Compra Ae!</title>

    <%@ include file="/layout/estilos.jsp" %>
</head>
<body>

<main>
    <%@ include file="/layout/navbar.jsp" %>
</main>


<div class="container" style="padding-top: 1rem;">

    <div class="row">
        <div class="mt-5 col-md-6 offset-md-3 d-flex justify-content-between">
            <h1 class="">Pedidos</h1>
            <a href="/account" class="btn btn-outline-success btn-sm">
                Ir para Conta
            </a>
        </div>
        <div class="mt-5 col-md-6 offset-md-3">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Número do Pedido</th>
                    <th scope="col">Total</th>
                    <th scope="col">Data</th>
                    <th scope="col">Ações</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${Orders}" var="order">
                    <tr>
                        <td scope="row">Pedido #<c:out value="${order.id}" /></td>
                        <td><c:out value="${order.getTotalFormated()}" /></td>
                        <td><c:out value="${order.getDateFormated()}" /></td>
                        <td style="width: 15px">
                            <div class="btn-group btn-group-sm" role="group">
                                <a class="btn btn-sm btn-primary" href="?action=show&id=<c:out value="${order.id}" />" title="Ver">
                                    <i class="bi bi-eye-fill"></i>
                                </a>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>
