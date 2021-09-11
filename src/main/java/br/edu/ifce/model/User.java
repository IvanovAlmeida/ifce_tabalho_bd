package br.edu.ifce.model;

import br.edu.ifce.annotations.NotMapped;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class User {
    public Integer id;
    public String name;
    public String email;
    public String password;
    public Integer type;

    @NotMapped
    public Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean checkPassword(String plainPassword) {
        SCryptPasswordEncoder sCrypt = new SCryptPasswordEncoder();
        return sCrypt.matches(plainPassword, password);
    }

    public boolean isAdmin() {
        return type == 1;
    }

    public String toString() {
        return "{ id = " + id + ", name = " + name + ", type = " + type + ", email = " + email + "  }";
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
