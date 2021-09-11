<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Conta - Compra Ae!</title>

    <%@ include file="/layout/estilos.jsp" %>
</head>
<body>

<main>
    <%@ include file="/layout/navbar.jsp" %>
</main>

<div class="container" style="padding-top: 1rem">
    <div class="row">
        <div class="mt-5 col-md-6 offset-md-3 d-flex justify-content-between">
            <h1 class="">Minha Conta</h1>
            <a href="/account/orders" class="btn btn-outline-success btn-sm">
                Ver meus Pedidos
            </a>
        </div>

        <div class="col-md-6 offset-md-3 mt-2">
            <form class="ui form" style="margin-top: 10px;" method="post" action="/account">
                <c:if test="${errors.size() > 0}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <ul class="list">
                            <c:forEach items="${errors}" var="error">
                                <li><c:out value="${error}" /></li>
                            </c:forEach>
                        </ul>
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <div class="row mb-3">
                    <div class="col-12">
                        <h4>Meus dados</h4>
                        <hr>
                    </div>
                    <div class="col-6">
                        <label class="form-label" for="name">Nome</label>
                        <input class="form-control" type="text" name="name" id="name" value="<c:out value="${User.name}" />" readonly />
                    </div>
                    <div class="col-6">
                        <label class="form-label" for="email">Email</label>
                        <input class="form-control" type="email" name="email" id="email" value="<c:out value="${User.email}" />" readonly />
                    </div>

                    <div class="col-12 mt-4">
                        <h4>Endereço</h4>
                        <hr>
                    </div>
                    <input type="hidden" name="user_id" value="<c:out value="${User.id}" />" />
                    <input type="hidden" name="id" value="<c:out value="${User.address.id}" />" />

                    <div class="col-6">
                        <label class="form-label" for="street">Rua</label>
                        <input class="form-control" type="text" name="street" id="street" value="<c:out value="${User.address.street}" />" required />
                    </div>

                    <div class="col-6">
                        <label class="form-label" for="number">Número</label>
                        <input class="form-control" type="text" name="number" id="number" value="<c:out value="${User.address.number}" />" required />
                    </div>

                    <div class="col-6">
                        <label class="form-label" for="street">Bairro</label>
                        <input class="form-control" type="text" name="district" id="district" value="<c:out value="${User.address.district}" />" required />
                    </div>

                    <div class="col-6">
                        <label class="form-label" for="city">Cidade</label>
                        <input class="form-control" type="text" name="city" id="city" value="<c:out value="${User.address.city}" />" required />
                    </div>

                    <div class="col-6">
                        <label class="form-label" for="state">Estado</label>
                        <input class="form-control" type="text" name="state" id="state" value="<c:out value="${User.address.state}" />" required />
                    </div>

                    <div class="col-6">
                        <label class="form-label" for="zipcode">CEP</label>
                        <input class="form-control" type="text" name="zipcode" id="zipcode" value="<c:out value="${User.address.zipcode}" />" required />
                    </div>
                </div>

                <button class="btn btn-success" type="submit">Salvar</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>
