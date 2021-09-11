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
            <h1 class="">Pedido #<c:out value="${Order.id}" /></h1>
            <a href="/account/orders" class="btn btn-outline-success btn-sm">
                Voltar para Pedidos
            </a>
        </div>
        <div class="mt-5 col-md-6 offset-md-3">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${Order.items}" var="item">
                    <tr>
                        <td><c:out value="${item.item.name}" /></td>
                        <td><c:out value="${item.quantity}" /></td>
                        <td><c:out value="${item.getTotalFormated()}" /></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="1">
                        Compra efetuada em <c:out value="${Order.getDateFormated()}" />
                    </td>
                    <td style="text-align: right; font-size: 1.3rem; font-weight: bold;">Total</td>
                    <td><c:out value="${Order.getTotalFormated()}" /></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="mt-5 col-md-6 offset-md-3">
            <h5>Endereço de Entrega:</h5>
            <p>
                <c:out value="${Order.address}" />
            </p>
        </div>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>
