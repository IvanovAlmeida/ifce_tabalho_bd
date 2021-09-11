package br.edu.ifce.controller;

import br.edu.ifce.dao.ItemDAO;
import br.edu.ifce.model.Item;
import br.edu.ifce.utils.BeanUtilities;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "ItemController", value = "/items")
public class ItemController extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private final ItemDAO itemDAO;

	public ItemController() {
		super();

		itemDAO = new ItemDAO();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		needsAuthentication = true;
		needsAdminAuth = true;

		super.service(req, resp);
	}

	@Override
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if(action != null && action.equals("add")) {
			request.setAttribute("item", new Item());
			View("views/item/add.jsp");
			return;
		}

		if(action != null && action.equals("edit")) {
			int id = parseInt(request.getParameter("id"));

			Item item = itemDAO.FindItem(id);
			request.setAttribute("item", item);

			View("views/item/add.jsp");
			return;
		}

		if(action != null && action.equals("disable")) {
			int id = parseInt(request.getParameter("id"));
			itemDAO.Disable(id);

			response.sendRedirect("/items");
			return;
		}

		if(action != null && action.equals("enable")) {
			int id = parseInt(request.getParameter("id"));
			itemDAO.Enable(id);

			response.sendRedirect("/items");
			return;
		}

		List<Item> items = itemDAO.ListAll(true);
		request.setAttribute("items", items);
		View("views/item/index.jsp");
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Item item = new Item();
		BeanUtilities.populateBean(item, request.getParameterMap());

		if(item.getId() == 0) {
			itemDAO.Insert(item);
			response.sendRedirect("/items");
		}

		if(item.getId() != 0) {
			itemDAO.Update(item);
			response.sendRedirect("/items");
		}
    }
}
