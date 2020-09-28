package com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper;

import com.github.rakhmedovrs.spring5mvcrest.domain.Product;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
@Mapper
public interface ProductMapper
{
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	ProductDTO productToProductDTO(Product product);

	Product productDTOToProduct(ProductDTO productDTO);
}
