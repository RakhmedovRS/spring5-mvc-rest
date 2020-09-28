package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductDTO;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductListDTO;
import com.github.rakhmedovrs.spring5mvcrest.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController
{
	public static final String BASE_URL = "/api/v1/products";

	private final ProductService productService;

	public ProductController(ProductService productService)
	{
		this.productService = productService;
	}

	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO getProductById(@PathVariable Long id)
	{
		return productService.getProductById(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ProductListDTO getAllProducts()
	{
		return new ProductListDTO(productService.getAllProducts());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO createNewProduct(@RequestBody ProductDTO productDTO)
	{
		return productService.createProduct(productDTO);
	}

	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO updateProduct(@PathVariable Long id,
	                                @RequestBody ProductDTO productDTO)
	{
		return productService.saveProductByDTO(id, productDTO);
	}

	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO patchProduct(@PathVariable Long id,
	                                 @RequestBody ProductDTO productDTO)
	{
		return productService.patchProduct(id, productDTO);
	}

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id)
	{
		productService.deleteProductById(id);
	}
}
