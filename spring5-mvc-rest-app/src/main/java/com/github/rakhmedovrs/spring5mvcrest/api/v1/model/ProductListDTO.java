package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
@Data
@AllArgsConstructor
public class ProductListDTO
{
	List<ProductDTO> products;
}
