package br.edu.ifce.model;

import br.edu.ifce.utils.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class Item {
	public Integer id = 0;
	public String name;
	public String description;
	public String image;
	public BigDecimal price;
	public Boolean active;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(@NotNull String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(@NotNull String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(@NotNull BigDecimal price) {
		this.price = price;
	}

	public String getFormatedPrice() {
		return StringUtils.FormatForMoney(price);
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public String toString()
	{
		return "Item: { id = " + id + ", name = " + name + ", description = " + description + ", price = " + price + " }";
	}
}
