package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductDTO;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
public interface ProductService
{
	List<ProductDTO> getAllProducts();

	ProductDTO createProduct(ProductDTO productDTO);

	void deleteProductById(Long id);

	ProductDTO getProductById(Long id);

	ProductDTO patchProduct(Long id, ProductDTO productDTO);

	ProductDTO saveProductByDTO(Long id, ProductDTO productDTO);
}
