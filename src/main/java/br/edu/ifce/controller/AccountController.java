package br.edu.ifce.controller;

import br.edu.ifce.dao.AddressDAO;
import br.edu.ifce.dao.UserDAO;
import br.edu.ifce.model.Address;
import br.edu.ifce.model.User;
import br.edu.ifce.utils.BeanUtilities;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AccountController", value = "/account")
public class AccountController extends BaseServlet {

    private final UserDAO userDAO;
    private final AddressDAO addressDAO;

    public AccountController() {
        userDAO = new UserDAO();
        addressDAO = new AddressDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        needsAuthentication = true;

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = userDAO.FindByEmail(GetUserEmail());
        user.address = addressDAO.FindByUserId(user.getId());

        if(user.address == null)
            user.address = new Address();

        request.setAttribute("User", user);

        View("views/account/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Address address = new Address();
        BeanUtilities.populateBean(address, request);

        address.setUseId(GetUserId());
        if(address.id == 0) {
            addressDAO.Insert(address);
        } else {
            addressDAO.Update(address);
        }

        response.sendRedirect("/account");
    }
}
