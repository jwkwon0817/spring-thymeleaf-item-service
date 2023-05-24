package com.codemos.springthymeleafitemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST: Fast Shipping
 * NORMAL: Normal Shipping
 * SLOW: Slow Shipping
 */
@Data
@AllArgsConstructor
public class DeliveryCode {
	private String code;
	private String displayName;
}
