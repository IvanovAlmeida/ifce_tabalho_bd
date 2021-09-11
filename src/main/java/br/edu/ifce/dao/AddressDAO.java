package br.edu.ifce.dao;

import br.edu.ifce.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressDAO extends GenericDAO {
    public boolean Insert(Address address) {
        String sql = "INSERT INTO address (street, number, district, city, state, zipcode, user_id) ";
        sql += " VALUES (?, ?, ?, ?, ?, ?, ?)";

        ArrayList<Object> values = new ArrayList<>();
        values.add(address.getStreet());
        values.add(address.getNumber());
        values.add(address.getDistrict());
        values.add(address.getCity());
        values.add(address.getState());
        values.add(address.getZipcode());
        values.add(address.getUserId());

        return ExecuteCommand(sql, values);
    }

    public boolean Update(Address address) {
        String sql = "UPDATE address SET street = ?, number = ?, district = ?, city = ?, state = ?, zipcode = ? ";
        sql += " WHERE id = ?";

        ArrayList<Object> values = new ArrayList<>();
        values.add(address.getStreet());
        values.add(address.getNumber());
        values.add(address.getDistrict());
        values.add(address.getCity());
        values.add(address.getState());
        values.add(address.getZipcode());
        values.add(address.getId());

        return ExecuteCommand(sql, values);
    }

    public Address FindByUserId(int user_id) {
        String sql = "SELECT * FROM address WHERE user_id = ?";
        ArrayList<Object> values = new ArrayList<>();
        values.add(user_id);

        List<Address> list = GenericSelect(sql, values, Address.class);

        return list.isEmpty() ? null : list.get(0);
    }
}
