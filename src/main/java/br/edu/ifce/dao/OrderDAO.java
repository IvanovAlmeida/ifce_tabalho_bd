package br.edu.ifce.dao;

import br.edu.ifce.model.Item;
import br.edu.ifce.model.Order;
import br.edu.ifce.model.OrderItem;
import br.edu.ifce.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO extends GenericDAO {

    public Order GetOrderById(int orderId, int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND id = ?";

        ArrayList<Object> values = new ArrayList<>();
        values.add(userId);
        values.add(orderId);

        List<Order> orders = GenericSelect(sql, values, Order.class);

        if(orders.isEmpty())
            return null;

        Order order = orders.get(0);

        LoadOrderItems(order);

        return order;
    }

    private void LoadOrderItems(Order order) {
        String sql = "SELECT * FROM orders_items where order_id = ?";
        ArrayList<Object> values = new ArrayList<>();
        values.add(order.id);

        List<OrderItem> orderItems = GenericSelect(sql, values, OrderItem.class);

        LoadItems(orderItems);

        order.setItems(orderItems);
    }

    private void LoadItems(List<OrderItem> orderItems) {
        List<Integer> ids = orderItems.stream()
                .map(o -> o.item_id).collect(Collectors.toList());

        String strIds = ids.stream().map(String::valueOf)
                .collect(Collectors.joining(", "));

        String sql = "SELECT * FROM items WHERE id IN (" + strIds + ")";

        List<Item> items = GenericSelect(sql, null, Item.class);

        for (Item item: items ) {
            (orderItems.stream()
                    .filter(o -> o.item_id.equals(item.id)).findFirst().get()).item = item;
        }
    }

    public List<Order> GetOrdersByUser(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ?";

        ArrayList<Object> values = new ArrayList<>();
        values.add(userId);

        return GenericSelect(sql, values, Order.class);
    }

    public boolean RegisterOrder(Order order) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionFactory.getConnection();
            conn.setAutoCommit(false);
            conn.getTransactionIsolation();

            /* Register order */
            String sql = "INSERT INTO orders (user_id, total, address) VALUES (?, ?, ?) RETURNING ID";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, order.getUserId());
            stmt.setObject(2, order.getTotal());
            stmt.setObject(3, order.getAddress());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            order.setId(rs.getInt(1));
            rs.close();
            stmt.close();

            /* Register order items */
            sql = "INSERT INTO orders_items (order_id, item_id, quantity, itemprice, totalprice)";
            sql += "  VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);

            for (OrderItem oi: order.getItems()) {
                stmt.setObject(1, order.getId());
                stmt.setObject(2, oi.item_id);
                stmt.setObject(3, oi.quantity);
                stmt.setObject(4, oi.itemPrice);
                stmt.setObject(5, oi.totalPrice);

                if(oi.item_id == 1)
                    throw new Exception("Para aqui");

                stmt.executeUpdate();
                stmt.clearParameters();
            }

            conn.commit();
            return true;
        } catch (Exception ex) {
            conn.rollback();
            System.err.println("Erro: " + ex);
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }

        return false;
    }

}
