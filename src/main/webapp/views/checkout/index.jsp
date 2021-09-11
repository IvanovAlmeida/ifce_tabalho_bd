<%@ page import="br.edu.ifce.model.Item" %>
<%@ page import="br.edu.ifce.utils.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Itens - Compra Ae!</title>

    <%@ include file="/layout/estilos.jsp" %>
</head>
<body>

<main>
    <%@ include file="/layout/navbar.jsp" %>
</main>

<div class="container" style="padding-top: 1rem;">
    <% if(request.getAttribute("success").toString().toLowerCase().equals("true")) { %>
    <div class="row">
        <div class="mt-5 col-md-6 offset-md-3">
            <h1 class="text-center">Pedido #<c:out value="${OrderId}" /> finalizado com sucesso!</h1>
        </div>
        <div class="mt-5 col-md-6 offset-md-3 d-flex justify-content-between">
            <table class="table">
                <thead>
                <tr>
                    <th class="">Nome</th>
                    <th>Valor Unitário</th>
                    <th>Quantidade</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${Cart}" var="item">
                    <tr>
                        <td><c:out value="${item.item.name}" /></td>
                        <td><c:out value="${item.item.getFormatedPrice()}" /></td>
                        <td><c:out value="${item.quantity}" /></td>
                        <td><c:out value="${item.getFormatedPrice()}" /></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2"></td>
                    <td style="text-align: right; font-size: 1.3rem; font-weight: bold;">Total</td>
                    <td><c:out value="${TotalValue}" /></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div class="mt-5 col-md-6 offset-md-3">
            <h5>Endereço de Entrega:</h5>
            <p>
                <c:out value="${Address}" />
            </p>
        </div>

        <div class="mt-5 col-md-6 offset-md-3 mt-3">
            <div class="d-grid gap-2">
                <a class="btn btn-success" href="/account/orders">Ir para pedidos</a>
            </div>
        </div>
    </div>
    <% } else { %>
    <div class="row mt-5">
        <div class="mt-5 col-md-6 offset-md-3">
            <h1 class="text-center text-danger">Ops, ocorreu um erro ao finalizar sua compra!</h1>
        </div>
        <div class="mt-5 col-md-6 offset-md-3 mt-3">
            <div class="d-grid gap-2">
                <a class="btn btn-success" href="/cart">Voltar para o carrinho.</a>
            </div>
        </div>
    </div>
    <% } %>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>

