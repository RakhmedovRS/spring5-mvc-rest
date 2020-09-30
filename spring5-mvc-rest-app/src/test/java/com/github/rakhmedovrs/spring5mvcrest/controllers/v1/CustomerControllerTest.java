package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.github.rakhmedovrs.spring5mvcrest.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.services.CustomerService;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
class CustomerControllerTest
{
	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerController customerController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController)
			.setControllerAdvice(new RestResponseEntityExceptionHandler())
			.build();
	}

	@Test
	public void testListCategories() throws Exception
	{
		CustomerDTO joe = new CustomerDTO();
		joe.setId(1L);
		joe.setFirstName("Joe");
		joe.setLastName("Newman");
		joe.setCustomerUrl(CustomerController.BASE_URL + "/" + +joe.getId());

		CustomerDTO jesse = new CustomerDTO();
		jesse.setId(2L);
		jesse.setFirstName("Jesse");
		jesse.setLastName("Pinkman");
		jesse.setCustomerUrl(CustomerController.BASE_URL + "/" + +jesse.getId());

		CustomerDTO walter = new CustomerDTO();
		walter.setId(3L);
		walter.setFirstName("Walter");
		walter.setLastName("White");
		walter.setCustomerUrl(CustomerController.BASE_URL + "/" + +walter.getId());

		List<CustomerDTO> customers = Arrays.asList(joe, jesse, walter);

		when(customerService.getAllCustomers()).thenReturn(customers);

		mockMvc.perform(get(CustomerController.BASE_URL)
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.customers", hasSize(3)));
	}

	@Test
	public void testGetByNameCategories() throws Exception
	{
		CustomerDTO joe = new CustomerDTO();
		joe.setId(1L);
		joe.setFirstName("Joe");
		joe.setLastName("Newman");
		joe.setCustomerUrl(CustomerController.BASE_URL + "/" + +joe.getId());

		when(customerService.getCustomerById(anyLong())).thenReturn(joe);

		mockMvc.perform(get(CustomerController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", equalTo(1)))
			.andExpect(jsonPath("$.firstName", equalTo(joe.getFirstName())))
			.andExpect(jsonPath("$.lastName", equalTo(joe.getLastName())))
			.andExpect(jsonPath("$.customerUrl", equalTo(joe.getCustomerUrl())));
	}

	@Test
	public void createNewCustomer() throws Exception
	{
		//given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + "1");

		when(customerService.createCustomer(customer)).thenReturn(returnDTO);

		//when/then
		mockMvc.perform(post(CustomerController.BASE_URL + "/")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(customer)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
			.andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())))
			.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/" + "1")));
	}

	@Test
	public void testUpdateCustomer() throws Exception
	{
		//given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + "1");

		when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		//when/then
		mockMvc.perform(put(CustomerController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(customer)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", equalTo("Fred")))
			.andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
			.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/" + "1")));
	}

	@Test
	public void testPatchCustomer() throws Exception
	{
		//given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName("Flintstone");
		returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + "1");

		when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

		//when/then
		mockMvc.perform(patch(CustomerController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(customer)))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.firstName", equalTo("Fred")))
			.andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
			.andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/" + "1")));
	}

	@Test
	public void testDeleteCustomer() throws Exception
	{
		mockMvc.perform(delete(CustomerController.BASE_URL + "/" + "1")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());

		verify(customerService).deleteCustomerById(anyLong());
	}

	@Test
	public void testGetByNameNotFound() throws Exception
	{
		when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

		mockMvc.perform(get(CustomerController.BASE_URL + "/222")
			.accept(MediaType.APPLICATION_JSON)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound());
	}
}