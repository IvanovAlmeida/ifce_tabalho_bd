<%@ page import="br.edu.ifce.utils.StringUtils" %>
<%@ page import="br.edu.ifce.model.CartItem" %>
<%@ page import="java.math.BigDecimal" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<%
    List<CartItem> items = (List<CartItem>)request.getSession().getAttribute("cart");
%>

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

    <div class="row">
        <div class="mt-5 col-md-8 offset-md-2">
            <div class="row">
                <div class="col-12 mb-5 d-flex">
                    <h1 class="me-auto">Meu Carrinho</h1>
                    <a href="/" class="btn btn-sm btn-outline-primary text-end">
                        Continuar Comprando
                    </a>
                </div>

                <div class="col-md-8">
                    <% if(items != null && items.size() > 0) { %>
                    <table class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="">Nome</th>
                            <th>Preço</th>
                            <th>Quantidade</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${items}" var="item">
                            <tr>
                                <td><c:out value="${item.item.name}" /></td>
                                <td><c:out value="${item.item.getFormatedPrice()}" /></td>
                                <td style="width: 10px;">
                                    <form action="" method="post">
                                        <div class="input-group mb-3">
                                            <input type="hidden" name="id" value="<c:out value="${item.itemId}" />">
                                            <input type="number" name="quantity" value="<c:out value="${item.quantity}" />" class="form-control" aria-describedby="button-addon2" />
                                            <button class="btn btn-green" type="submit" id="button-addon2">
                                                <i class="bi bi-arrow-clockwise"></i>
                                            </button>
                                        </div>
                                    </form>
                                </td>
                                <td style="width: 15px">
                                    <div class="btn-group btn-group-sm">
                                        <a class="btn btn-sm btn-danger" href="?action=remove&id=<c:out value="${item.itemId}" />" title="Remover">
                                            <i class="bi bi-trash-fill"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="2"></td>
                            <td style="text-align: right; font-size: 1.3rem; font-weight: bold;">Total</td>
                            <td>
                                <%

                                    BigDecimal totalValue = BigDecimal.ZERO;
                                    for(CartItem i : items) {
                                        totalValue = totalValue.add(i.getTotalPrice());
                                    }

                                    String formatedValue = StringUtils.FormatForMoney(totalValue);
                                %>

                                <%= formatedValue %>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="d-grid gap-2">
                        <c:if test="${address != null}">
                            <a class="btn btn-success" href="${pageContext.request.contextPath}/checkout">Finalizar Compra</a>
                        </c:if>
                        <c:if test="${address == null}">
                            <a class="btn btn-success disabled" aria-disabled="false">Finalizar Compra</a>
                        </c:if>
                    </div>
                    <% } else { %>
                    <h1 class="text-center mt-5">Seu carrinho está vazio!</h1>
                    <% } %>
                </div>

                <div class="col-md-4">
                    <h5>Endereço de Entrega:</h5>
                    <c:if test="${address != null}">
                        <c:out value="${address}" />
                    </c:if>
                    <c:if test="${address == null}">
                        Você não possui nenhum endereço cadastrado.
                        Vá até as <a href="${pageContext.request.contextPath}/account">informações da sua conta</a> e cadastre um endereço.
                    </c:if>
                </div>

            </div>
        </div>

    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>

