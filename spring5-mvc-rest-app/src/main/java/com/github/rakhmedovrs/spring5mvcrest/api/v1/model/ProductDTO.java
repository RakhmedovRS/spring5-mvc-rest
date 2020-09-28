package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO
{
	private Long id;
	private String name;
	private Double price;
	private String categoryURL;
	private String vendorURL;
}
