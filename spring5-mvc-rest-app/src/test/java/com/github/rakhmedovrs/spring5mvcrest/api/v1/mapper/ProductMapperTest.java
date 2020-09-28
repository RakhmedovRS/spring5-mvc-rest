package com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductDTO;
import com.github.rakhmedovrs.spring5mvcrest.domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
class ProductMapperTest
{
	public static final long ID = 1L;
	public static final String NAME = "Ivan";
	public static final Double PRICE = 1D;

	ProductMapper productMapper = ProductMapper.INSTANCE;

	@Test
	public void productToProductDTO()
	{
		//given
		Product product = new Product();
		product.setId(ID);
		product.setName(NAME);
		product.setPrice(PRICE);

		//when
		ProductDTO productDTO = productMapper.productToProductDTO(product);

		//then
		assertEquals(ID, productDTO.getId());
		assertEquals(NAME, productDTO.getName());
		assertEquals(PRICE, productDTO.getPrice());
	}
}