<%@ page import="br.edu.ifce.model.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" isELIgnored="false" contentType="text/html; utf-8" pageEncoding="utf-8" %>

<%
    List<CartItem> cart = (List<CartItem>)request.getSession().getAttribute("cart");
    String quantityItems = "";

    if(cart != null && cart.size() > 0)
        quantityItems = "(" + cart.size() + ")";
%>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="h4 d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                Compra Ae! &nbsp;
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <% if (request.getSession().getAttribute("name") != null && request.getSession().getAttribute("type").toString().equals("1")) { %>
                <li><a href="/items" class="nav-link px-2 text-white">Estoque</a></li>
                <% } %>
            </ul>

            <div class="text-end">
                <% if (request.getSession().getAttribute("name") == null) { %>
                <a type="button" class="btn btn-outline-light me-2" href="/register">Registrar-se</a>
                <a type="button" class="btn btn-warning" href="/login">Entrar</a>
                <% } else { %>
                <a type="button" class="btn btn-warning" href="/cart">
                    Carrinho <%= quantityItems %>
                    <i class="bi bi-cart3"></i>
                </a>
                <% } %>
            </div>
            <% if (request.getSession().getAttribute("name") != null) { %>
            <div class="ms-2 dropdown">
                <a class="btn btn-warning dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    <%= request.getSession().getAttribute("name").toString()  %>
                </a>

                <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                    <li><a class="dropdown-item" href="/account">Minha Conta</a></li>
                    <li><a class="dropdown-item" href="/account/orders">Meus Pedidos</a></li>
                    <li>
                        <a class="dropdown-item" href="/login?action=logout">Sair</a>
                    </li>
                </ul>
            </div>
            <% } %>
        </div>
    </div>
</header>