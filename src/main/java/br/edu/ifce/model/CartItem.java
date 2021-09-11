package br.edu.ifce.model;

import br.edu.ifce.utils.StringUtils;

import java.math.BigDecimal;

public class CartItem {
    private Item item;
    private Integer itemId;
    private Integer quantity;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.itemId = item.getId();
        this.item = item;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice()
    {
        return item.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public String getFormatedPrice()
    {
        return StringUtils.FormatForMoney(getTotalPrice());
    }
}
