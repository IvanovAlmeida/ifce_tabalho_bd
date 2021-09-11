package br.edu.ifce.dao;

import br.edu.ifce.model.User;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserDAO extends GenericDAO {

    public boolean Insert(User user) {
        SCryptPasswordEncoder sCrypt = new SCryptPasswordEncoder();
        user.setPassword(sCrypt.encode(user.getPassword()));

        String sql = "INSERT INTO users (name, email, password, type) VALUES (?, ?, ?, ?)";
        ArrayList<Object> values = new ArrayList<>();
        values.add(user.getName());
        values.add(user.getEmail());
        values.add(user.getPassword());
        values.add(user.getType());

        return ExecuteCommand(sql, values);
    }

    public boolean VerifyIfUsedEmail(String email) {
        User user = FindByEmail(email);
        return user != null;
    }

    public User FindByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        ArrayList<Object> values = new ArrayList<>();
        values.add(email);

        List<User> list = GenericSelect(sql, values, User.class);

        return list.isEmpty() ? null : list.get(0);
    }
}
