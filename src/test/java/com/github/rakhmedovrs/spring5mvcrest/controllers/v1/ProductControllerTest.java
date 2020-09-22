package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.ProductDTO;
import com.github.rakhmedovrs.spring5mvcrest.services.ProductService;
import com.github.rakhmedovrs.spring5mvcrest.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.github.rakhmedovrs.spring5mvcrest.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author RakhmedovRS
 * @created 25-Aug-20
 */
class ProductControllerTest
{
	@Mock
	ProductService productService;

	@InjectMocks
	ProductController productController;

	MockMvc mockMvc;

	@BeforeEach
	void setUp()
	{
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController)
			.setControllerAdvice(new RestResponseEntityExceptionHandler())
			.build();
	}

	@Test
	public void testListProduct() throws Exception
	{
		ProductDTO apples = new ProductDTO();
		apples.setName("Apples");
		apples.setPrice(5D);

		ProductDTO oranges = new ProductDTO();
		oranges.setName("Oranges");
		oranges.setPrice(4D);

		ProductDTO limes = new ProductDTO();
		limes.setName("Limes");
		limes.setPrice(7D);

		ProductDTO bananas = new ProductDTO();
		bananas.setName("Bananas");
		bananas.setPrice(2D);

		List<ProductDTO> productDTOS = Arrays.asList(apples, oranges, limes, bananas);

		when(productService.getAllProducts()).thenReturn(productDTOS);

		mockMvc.perform(get(ProductController.BASE_URL)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.products", hasSize(4)));
	}

	@Test
	public void testGetByProductById() throws Exception
	{
		ProductDTO apples = new ProductDTO();
		apples.setId(1L);
		apples.setName("Apples");
		apples.setPrice(5D);

		when(productService.getProductById(anyLong())).thenReturn(apples);

		mockMvc.perform(get(ProductController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", equalTo(1)))
			.andExpect(jsonPath("$.name", equalTo(apples.getName())))
			.andExpect(jsonPath("$.price", equalTo(apples.getPrice())));
	}

	@Test
	public void testCreateNewProduct() throws Exception
	{
		//given
		ProductDTO apples = new ProductDTO();
		apples.setName("Apples");
		apples.setPrice(5D);

		ProductDTO returnDTO = new ProductDTO();
		returnDTO.setName(apples.getName());
		returnDTO.setPrice(apples.getPrice());

		when(productService.createProduct(apples)).thenReturn(returnDTO);

		//when/then
		mockMvc.perform(post(ProductController.BASE_URL + "/")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(apples)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name", equalTo(apples.getName())))
			.andExpect(jsonPath("$.price", equalTo(apples.getPrice())));
	}

	@Test
	public void testUpdateProduct() throws Exception
	{
		//given
		ProductDTO apples = new ProductDTO();
		apples.setName("Apples");
		apples.setPrice(5D);

		ProductDTO returnDTO = new ProductDTO();
		returnDTO.setName(apples.getName());
		returnDTO.setPrice(apples.getPrice());

		when(productService.saveProductByDTO(anyLong(), any(ProductDTO.class))).thenReturn(returnDTO);

		//when/then
		mockMvc.perform(put(ProductController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(apples)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.name", equalTo(apples.getName())))
			.andExpect(jsonPath("$.price", equalTo(apples.getPrice())));
	}

	@Test
	public void testPatchProduct() throws Exception
	{
		//given
		ProductDTO apples = new ProductDTO();
		apples.setName("Apples");
		apples.setPrice(5D);

		ProductDTO returnDTO = new ProductDTO();
		returnDTO.setName(apples.getName());
		returnDTO.setPrice(apples.getPrice());

		when(productService.patchProduct(anyLong(), any(ProductDTO.class))).thenReturn(returnDTO);

		//when/then
		mockMvc.perform(patch(ProductController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(apples)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", equalTo(apples.getName())))
			.andExpect(jsonPath("$.price", equalTo(apples.getPrice())));
	}

	@Test
	public void testDeleteProduct() throws Exception
	{
		mockMvc.perform(delete(ProductController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(productService).deleteProductById(anyLong());
	}

	@Test
	public void testGetByIdNotFound() throws Exception
	{
		when(productService.getProductById(anyLong())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(ProductController.BASE_URL + "/222")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
}