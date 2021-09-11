package br.edu.ifce.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AcessoNegado", value = "/acesso-negado")
public class AcessoNegadoController extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        View("views/acesso-negado.jsp");
    }
}
