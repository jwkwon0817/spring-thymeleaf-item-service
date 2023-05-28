package com.codemos.springthymeleafitemservice.domain.item;

import lombok.Data;

import java.util.List;

@Data
public class Item {
	private Long id;
	private String itemName;
	private Integer price;
	private Integer quantity;

	private Boolean open; // Whether to Sale
	private List<String> regions; // Registration Area
	private ItemType itemType; // Item Type
	private String deliveryCode; // Delivery Code

	public Item() {
	}

	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
