package br.edu.ifce.dao;

import br.edu.ifce.model.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ItemDAO extends GenericDAO {
    public Item FindItem(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";

        ArrayList<Object> values = new ArrayList<>();
        values.add(id);

        List<Item> items = GenericSelect(sql, values, Item.class);

        return items.isEmpty() ? null : items.get(0);
    }

    public List<Item> ListAll(boolean includeDisableds) {
        String sql = "SELECT * FROM items";
        if(!includeDisableds)
            sql += " WHERE active = true";

        sql += " ORDER BY active DESC, name ASC";

        return GenericSelect(sql, null, Item.class);
    }

    public boolean Insert(@NotNull Item item) {
        String sql = "INSERT INTO items (name, description, image, price) VALUES (?, ?, ?, ?)";
        ArrayList<Object> values = new ArrayList<>();
        values.add(item.getName());
        values.add(item.getDescription());
        values.add(item.getImage());
        values.add(item.getPrice());

        return ExecuteCommand(sql, values);
    }

    public boolean Update(@NotNull Item item) {
        String sql = "UPDATE items SET name = ?, description = ?, image = ?, price = ?";
        sql += " WHERE id = ?";

        ArrayList<Object> values = new ArrayList<>();
        values.add(item.getName());
        values.add(item.getDescription());
        values.add(item.getImage());
        values.add(item.getPrice());
        values.add(item.getId());

        return ExecuteCommand(sql, values);
    }

    public boolean Disable(int id) {
        String sql = "UPDATE items SET active = false WHERE id = ?";

        ArrayList<Object> values = new ArrayList<>();
        values.add(id);

        return ExecuteCommand(sql, values);
    }

    public boolean Enable(int id) {
        String sql = "UPDATE items SET active = true WHERE id = ?";

        ArrayList<Object> values = new ArrayList<>();
        values.add(id);

        return ExecuteCommand(sql, values);
    }
}
