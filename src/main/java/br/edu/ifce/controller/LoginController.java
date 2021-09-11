package br.edu.ifce.controller;

import br.edu.ifce.dao.UserDAO;
import br.edu.ifce.model.User;
import br.edu.ifce.utils.BeanUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends BaseServlet {
    private final UserDAO userDAO;

    public LoginController() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null && action.equals("logout")) {
            request.getSession().invalidate();
            response.sendRedirect("/login");
            return;
        }

        View("views/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User ru = new User();
        BeanUtilities.populateBean(ru, request);

        User u = userDAO.FindByEmail(ru.getEmail());
        if(u != null && u.checkPassword(ru.getPassword())) {
            SetSession(u, request.getSession());
            String returnUrl = request.getParameter("returnUrl");
            if(returnUrl != null) {
                System.out.println(request.getParameter("returnUrl"));
                response.sendRedirect(returnUrl);
            } else {
                if(u.isAdmin())
                    response.sendRedirect("/items");
                if(!u.isAdmin())
                    response.sendRedirect("/");
            }
            return;
        }

        AddError("Usuário ou Senha incorretos!");
        View("views/login.jsp");
    }

    private void SetSession(User u, HttpSession session) {
        session.setAttribute("id", u.getId());
        session.setAttribute("name", u.getName());
        session.setAttribute("email", u.getEmail());
        session.setAttribute("type", u.getType());
    }
}
