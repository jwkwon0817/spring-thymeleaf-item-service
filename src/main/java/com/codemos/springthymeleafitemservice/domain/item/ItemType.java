package com.codemos.springthymeleafitemservice.domain.item;

public enum ItemType {
	BOOK("Books"),
	FOOD("Food"),
	ETC("Etc");

	private final String description;

	ItemType(String description) {
		this.description = description;
	}
}
