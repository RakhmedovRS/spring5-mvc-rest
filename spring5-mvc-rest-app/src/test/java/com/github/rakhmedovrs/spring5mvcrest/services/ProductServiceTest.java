package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper.ProductMapper;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductDTO;
import com.github.rakhmedovrs.spring5mvcrest.domain.Product;
import com.github.rakhmedovrs.spring5mvcrest.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author RakhmedovRS
 * @created 25-Aug-20
 */
class ProductServiceTest
{
	public static final long ID = 1L;
	public static final String NAME = "Ivan";
	public static final Double PRICE = 1D;

	ProductService productService;

	@Mock
	ProductRepository productRepository;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);

		productService = new ProductServiceImpl(productRepository, ProductMapper.INSTANCE);
	}

	@Test
	public void testGetAllProducts()
	{
		//given
		List<Product> products = Arrays.asList(new Product(), new Product(), new Product());

		when(productRepository.findAll()).thenReturn(products);

		//when
		List<ProductDTO> productDTOS = productService.getAllProducts();

		//then
		assertEquals(3, productDTOS.size());
	}

	@Test
	public void testGetProductById()
	{
		//given
		Product product = new Product();
		product.setId(ID);
		product.setName(NAME);
		product.setPrice(PRICE);

		when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

		//when
		ProductDTO productDTO = productService.getProductById(ID);

		//then
		assertEquals(ID, productDTO.getId());
		assertEquals(NAME, productDTO.getName());
		assertEquals(PRICE, productDTO.getPrice());
	}

	@Test
	public void testCreateNewProduct()
	{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Strawberries");
		productDTO.setPrice(50D);

		Product savedProduct = new Product();
		savedProduct.setId(1L);
		savedProduct.setName(productDTO.getName());
		savedProduct.setPrice(productDTO.getPrice());

		when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

		ProductDTO savedDTO = productService.createProduct(productDTO);

		assertEquals(productDTO.getName(), savedDTO.getName());
		assertEquals(productDTO.getPrice(), savedDTO.getPrice());
	}

	@Test
	public void testSaveProduct()
	{
		ProductDTO productDTO = new ProductDTO();
		productDTO.setName("Strawberries");
		productDTO.setPrice(50D);

		Product savedProduct = new Product();
		savedProduct.setId(1L);
		savedProduct.setName(productDTO.getName());
		savedProduct.setPrice(productDTO.getPrice());

		when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

		ProductDTO savedDTO = productService.saveProductByDTO(1L, productDTO);

		assertEquals(productDTO.getName(), savedDTO.getName());
		assertEquals(productDTO.getPrice(), savedDTO.getPrice());
	}

	@Test
	public void testDeleteProductById()
	{
		productRepository.deleteById(1L);
		verify(productRepository, times(1)).deleteById(anyLong());
	}
}