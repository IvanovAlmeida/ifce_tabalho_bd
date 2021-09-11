package br.edu.ifce.model;

import br.edu.ifce.annotations.NotMapped;
import br.edu.ifce.utils.StringUtils;

import java.math.BigDecimal;

public class OrderItem {
    public Integer order_id;
    public Integer item_id;
    public Integer quantity;
    public BigDecimal itemPrice;
    public BigDecimal totalPrice;

    @NotMapped
    public Item item;

    public static OrderItem FromCartItem(CartItem cartItem) {
        OrderItem oi = new OrderItem();
        oi.item_id = cartItem.getItemId();
        oi.quantity = cartItem.getQuantity();
        oi.itemPrice = cartItem.getItem().getPrice();
        oi.totalPrice = cartItem.getTotalPrice();
        oi.item = cartItem.getItem();

        return  oi;
    }

    public int getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public String getTotalFormated() {
        return StringUtils.FormatForMoney(totalPrice);
    }
}
