package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper.ProductMapper;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductDTO;
import com.github.rakhmedovrs.spring5mvcrest.domain.Product;
import com.github.rakhmedovrs.spring5mvcrest.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
@Service
public class ProductServiceImpl implements ProductService
{
	private final ProductMapper productMapper;

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper)
	{
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductDTO> getAllProducts()
	{
		return productRepository
			.findAll()
			.stream()
			.map(productMapper::productToProductDTO)
			.collect(Collectors.toList());
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO)
	{
		Product product = productMapper.productDTOToProduct(productDTO);
		Product savedProduct = productRepository.save(product);
		return productMapper.productToProductDTO(savedProduct);
	}

	@Override
	public void deleteProductById(Long id)
	{
		productRepository.deleteById(id);
	}

	@Override
	public ProductDTO getProductById(Long id)
	{
		Product product = productRepository
			.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Can't find product with id - " + id));

		return productMapper.productToProductDTO(product);
	}

	@Override
	public ProductDTO patchProduct(Long id, ProductDTO productDTO)
	{
		return productRepository
			.findById(id)
			.map(product ->
			{
				product.setPrice(productDTO.getPrice() == null ? product.getPrice() : productDTO.getPrice());
				product.setName(productDTO.getName() == null ? product.getName() : productDTO.getName());
				return productMapper.productToProductDTO(product);
			})
			.orElseThrow(() -> new ResourceNotFoundException("Can't find product with id - " + id));
	}

	@Override
	public ProductDTO saveProductByDTO(Long id, ProductDTO productDTO)
	{
		Product product = productMapper.productDTOToProduct(productDTO);
		product.setId(id);
		Product saved = productRepository.save(product);
		return productMapper.productToProductDTO(saved);
	}
}
