package br.edu.ifce.controller;

import br.edu.ifce.dao.AddressDAO;
import br.edu.ifce.dao.ItemDAO;
import br.edu.ifce.dao.OrderDAO;
import br.edu.ifce.model.Address;
import br.edu.ifce.model.CartItem;
import br.edu.ifce.model.Order;
import br.edu.ifce.model.OrderItem;
import br.edu.ifce.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CheckoutController", value = "/checkout")
public class CheckoutController extends BaseServlet {
    private final ItemDAO itemDAO;
    private final AddressDAO addressDAO;
    private final OrderDAO orderDAO;

    public CheckoutController() {
        this.itemDAO = new ItemDAO();
        this.addressDAO = new AddressDAO();
        this.orderDAO = new OrderDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        needsAuthentication = true;

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CartItem> cart = (List<CartItem>)request.getSession().getAttribute("cart");

        if(cart == null || cart.isEmpty()) {
            response.sendRedirect("/cart");
            return;
        }

        BigDecimal valorTotal = GetTotalCartValue(cart);
        Address address = addressDAO.FindByUserId(GetUserId());

        Order order = ConvertCartToOrder(cart);
        order.setUserId(GetUserId());
        order.setAddress(address.toString());

        boolean success = false;
        try {
            success = orderDAO.RegisterOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("success", success);
        if(success) {
            request.setAttribute("OrderId", order.id);
            request.setAttribute("Address", address.toString());
            request.setAttribute("TotalValue", StringUtils.FormatForMoney(valorTotal));
            request.setAttribute("Cart", cart);

            request.getSession().setAttribute("cart", new ArrayList<CartItem>());
        } else {
            AddError("Ops, ocorreu um erro ao finalizar sua compra!");
        }

        View("views/checkout/index.jsp");
    }

    private Order ConvertCartToOrder(List<CartItem> cart) {
        Order order = new Order();
        order.setTotal(GetTotalCartValue(cart));

        for (CartItem ci : cart) {
            order.getItems().add(OrderItem.FromCartItem(ci));
        }

        return order;
    }

    private BigDecimal GetTotalCartValue(List<CartItem> cart) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for(CartItem item : cart) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }

        return totalPrice;
    }
}
