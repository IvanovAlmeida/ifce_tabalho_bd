package br.edu.ifce.controller;

import br.edu.ifce.dao.OrderDAO;
import br.edu.ifce.model.Item;
import br.edu.ifce.model.Order;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Console;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "OrdersController", value = "/account/orders")
public class OrdersController extends BaseServlet {
    private final OrderDAO orderDAO;

    public OrdersController() {
        orderDAO = new OrderDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        needsAuthentication = true;

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action != null && action.equals("show")) {
            int id = parseInt(request.getParameter("id"));

            Order order = orderDAO.GetOrderById(id, GetUserId());
            request.setAttribute("Order", order);

            View("../views/account/view_order.jsp");
            return;
        }


        List<Order> orders = orderDAO.GetOrdersByUser(GetUserId());

        request.setAttribute("Orders", orders);

        View("../views/account/orders.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
