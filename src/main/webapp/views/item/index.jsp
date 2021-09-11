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

    <div class="row">
        <div class="mt-5 col-md-6 offset-md-3 d-flex justify-content-between">
            <h1 class="">Itens</h1>
            <a href="?action=add" class="btn btn-outline-success btn-sm">
                Novo Item
            </a>
        </div>
        <div class="mt-5 col-md-6 offset-md-3">
            <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Preço</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${items}" var="item">
                    <tr class="<c:out value="${(item.active ? '' : 'table-danger')}" />">
                        <td scope="row"><c:out value="${item.id}" /></td>
                        <td><c:out value="${item.name}" /></td>
                        <td><c:out value="${item.getFormatedPrice()}" /></td>
                        <td style="width: 15px">
                            <div class="btn-group btn-group-sm" role="group">
                                <a class="btn btn-sm btn-warning" href="?action=edit&id=<c:out value="${item.id}" />" title="Editar">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                <c:if test="${item.active}">
                                <a class="btn btn-sm btn-danger" href="?action=disable&id=<c:out value="${item.id}" />" title="Desativar">
                                    <i class="bi bi-trash-fill"></i>
                                </a>
                                </c:if>
                                <c:if test="${!item.active}">
                                    <a class="btn btn-sm btn-success" href="?action=enable&id=<c:out value="${item.id}" />" title="Reativar">
                                        <i class="bi bi-reply-fill"></i>
                                    </a>
                                </c:if>
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

