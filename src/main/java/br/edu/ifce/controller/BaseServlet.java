package br.edu.ifce.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseServlet extends HttpServlet {
    private HttpServletRequest request;
    private HttpServletResponse response;
    protected List<String> errors = new ArrayList<>();

    protected boolean needsAuthentication = false;
    protected boolean needsAdminAuth = false;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        request = req;
        response = resp;
        errors.clear();

        if(needsAuthentication && !UserIsAuthenticated(req.getSession())) {
            String uri = req.getRequestURI();
            if(req.getQueryString() != null) {
                uri += "?" + req.getQueryString();
            }

            System.out.println(uri);

            resp.sendRedirect("/login?returnUrl=" + URLEncoder.encode(uri, "UTF-8"));
            return;
        }

        if(needsAuthentication && needsAdminAuth && !UserIsAdmin(req.getSession())) {
            resp.sendRedirect("/acesso-negado");
            return;
        }

        req.removeAttribute("errors");
        req.setAttribute("errors", errors);

        super.service(req, resp);
    }

    protected void AddError(String error) {
        errors.add(error);
    }

    protected void View(String viewPath) throws ServletException, IOException {

        System.out.println("ViewPath => " + viewPath);

        RequestDispatcher view = request.getRequestDispatcher(viewPath);
        view.forward(request, response);
    }

    @Override
    public void destroy() {
        request.removeAttribute("errors");
        errors.clear();
        super.destroy();
    }

    protected int GetUserId() {
        return Integer.parseInt(request.getSession().getAttribute("id").toString());
    }

    protected String GetUserEmail() {
        return request.getSession().getAttribute("email").toString();
    }

    protected Boolean UserIsAuthenticated(HttpSession session) {
        return session.getAttribute("id") != null;
    }

    protected Boolean UserIsAdmin(HttpSession session) {
        return UserIsAuthenticated(session)
                && session.getAttribute("type").toString().equals("1");
    }
}
