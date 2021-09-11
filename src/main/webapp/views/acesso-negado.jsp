<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Acesso Negado - Compra Ae!</title>

    <%@ include file="/layout/estilos.jsp" %>
</head>
<body>

<main>
    <%@ include file="/layout/navbar.jsp" %>
</main>

<div class="container" style="padding-top: 1rem">
    <div class="row text-center mt-5 col-md-6 offset-md-3">
        <h1 class="">Acesso Negado</h1>
        <hr>
    </div>

    <div class="col-md-6 offset-md-3 mt-2 text-center">
        <p>
            Você não pode acessar esse recurso!
        </p>
    </div>
</div>

<%@ include file="/layout/javascript.jsp" %>
</body>
</html>
