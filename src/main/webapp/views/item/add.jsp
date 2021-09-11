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
            <% if(request.getParameter("action").equals("add")) { %>
            <h1 class="">Cadastro</h1>
            <% } %>

            <% if(request.getParameter("action").equals("edit")) { %>
            <h1 class="">Alteração</h1>
            <% } %>

            <a href="?action=list" class="btn btn-outline-primary btn-sm">
                Voltar
            </a>
        </div>
        <div class="mt-5 col-md-6 offset-md-3">
            <form method="post">
                <input type="hidden" name="id" value="<c:out value="${(item.id == null ? 0 : item.id)}" />">
                <div class="mb-3">
                    <label class="form-label" for="name">Nome</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="Nome" required value="<c:out value="${item.name}" />">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="description">Descrição</label>
                    <input type="text" class="form-control" name="description" id="description" required placeholder="Descrição" value="<c:out value="${item.description}" />">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="price">Preço</label>
                    <input type="text" class="form-control" name="price" id="price" required placeholder="Preço" value="<c:out value="${item.price}" />">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="image">Imagem</label>
                    <input type="text" class="form-control" name="image" id="image" required placeholder="URL Imagem" value="<c:out value="${item.image}" />">
                </div>
                <div class="d-grid gap-2">
                    <button class="btn btn-success" type="submit">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>