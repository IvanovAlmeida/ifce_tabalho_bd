package br.edu.ifce.controller;

import br.edu.ifce.dao.AddressDAO;
import br.edu.ifce.dao.ItemDAO;
import br.edu.ifce.model.Address;
import br.edu.ifce.model.CartItem;
import br.edu.ifce.model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "CartController", value = "/cart")
public class CartController extends BaseServlet {
    private final ItemDAO itemDAO;
    private final AddressDAO addressDAO;
    private List<CartItem> cart;

    public CartController() {
        itemDAO = new ItemDAO();
        addressDAO = new AddressDAO();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        needsAuthentication = true;

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoadCart(request);

        String action = request.getParameter("action");
        String id = request.getParameter("id");

        if(action != null && action.equals("add") && id != null) {
            Item item = itemDAO.FindItem(parseInt(id));

            if(VerifyIfItemInCart(item, cart)) {
                UpdateQuantity(item, 1, cart);

                response.sendRedirect("/");
                return;
            }

            CartItem cartItem = new CartItem();
            cartItem.setItem(item);
            cartItem.setQuantity(1);

            cart.add(cartItem);

            request.getSession().setAttribute("cart", cart);

            response.sendRedirect("/");
            return;
        }

        if(action != null && action.equals("remove") && id != null) {
            Item item = itemDAO.FindItem(parseInt(id));

            if(!VerifyIfItemInCart(item, cart)) {
                response.sendRedirect("/cart");
                return;
            }

            CartItem cartItem = GetItemCart(item, cart);
            cart.remove(cartItem);
        }

        Address address = addressDAO.FindByUserId(GetUserId());

        request.setAttribute("address", address);
        request.setAttribute("items", cart);
        View("views/cart/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoadCart(request);

        String id = request.getParameter("id");
        String quantity = request.getParameter("quantity");

        if(id != null && quantity != null) {
            Item item = itemDAO.FindItem(parseInt(id));
            CartItem cartItem = GetItemCart(item, cart);

            if(cartItem != null) {
                cartItem.setQuantity(parseInt(quantity));
                if(cartItem.getQuantity() <= 0) {
                    cart.remove(cartItem);
                }
            }
        }

        request.getSession().setAttribute("cart", cart);
        request.setAttribute("items", cart);
        View("views/cart/index.jsp");
    }

    private void LoadCart(HttpServletRequest request) {
        cart = (List<CartItem>)request.getSession().getAttribute("cart");
        if(cart == null)
            cart = new ArrayList<>();
    }

    private boolean VerifyIfItemInCart(Item item, List<CartItem> cart) {
        for(CartItem cartItem : cart) {
            if(cartItem.getItemId() == item.getId())
                return true;
        }

        return false;
    }

    private CartItem GetItemCart(Item item, List<CartItem> cart) {
        for(CartItem cartItem : cart) {
            if(cartItem.getItemId() == item.getId())
                return cartItem;
        }

        return null;
    }

    private void UpdateQuantity(Item item, int quantity, List<CartItem> cart) {
        CartItem cartItem = GetItemCart(item, cart);

        if(cartItem != null)
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
    }
}
