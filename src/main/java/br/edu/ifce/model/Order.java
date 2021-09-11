package br.edu.ifce.model;

import br.edu.ifce.annotations.NotMapped;
import br.edu.ifce.utils.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Locale;

public class Order {
    public Integer id;
    public Integer user_id;
    public Date date;
    public BigDecimal total;
    public String address;

    @NotMapped
    private User user;
    @NotMapped
    private List<OrderItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public String getTotalFormated() {
        return StringUtils.FormatForMoney(total);
    }

    public String getDateFormated() {
        String pattern = "dd/MM/yyyy HH:mm";
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat(pattern, new Locale("pt", "BR"));
        return simpleDateFormat.format(date);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
