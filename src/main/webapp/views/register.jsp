<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Registrar - Compra Ae!</title>

        <%@ include file="/layout/estilos.jsp" %>
    </head>
    <body>

    <main>
        <%@ include file="/layout/navbar.jsp" %>
    </main>

        <div class="container" style="padding-top: 1rem">
            <div class="row text-center mt-5 col-md-6 offset-md-3">
                <h1 class="">Registre-se</h1>
                <hr>
            </div>

            <div class="col-md-6 offset-md-3 mt-2">
                <form class="ui form" style="margin-top: 10px;" method="post" action="/register">
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
                    <div class="mb-3">
                        <label class="form-label" for="name">Nome</label>
                        <input class="form-control" type="text" name="name" id="name" placeholder="Nome" required />
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="email">Email</label>
                        <input class="form-control" type="email" name="email" id="email" placeholder="fulano_de_tal@email.com" required />
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="password">Senha</label>
                        <input class="form-control" type="password" name="password" id="password" placeholder="*****" required>
                    </div>

                    <button class="btn btn-success" type="submit">Registrar</button>
                </form>
            </div>
        </div>

        <%@ include file="/layout/javascript.jsp" %>
    </body>
</html>
