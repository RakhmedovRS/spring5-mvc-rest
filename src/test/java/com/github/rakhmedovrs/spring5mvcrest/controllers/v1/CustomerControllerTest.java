package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.services.CustomerService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testListCategories() throws Exception
	{
		CustomerDTO joe = new CustomerDTO();
		joe.setId(1L);
		joe.setFirstName("Joe");
		joe.setLastName("Newman");
		joe.setCustomerUrl("/api/v1/customers" + joe.getId());

		CustomerDTO jesse = new CustomerDTO();
		jesse.setId(2L);
		jesse.setFirstName("Jesse");
		jesse.setLastName("Pinkman");
		jesse.setCustomerUrl("/api/v1/customers" + jesse.getId());

		CustomerDTO walter = new CustomerDTO();
		walter.setId(3L);
		walter.setFirstName("Walter");
		walter.setLastName("White");
		walter.setCustomerUrl("/api/v1/customers" + walter.getId());

		List<CustomerDTO> customers = Arrays.asList(joe, jesse, walter);

		when(customerService.getAllCustomers()).thenReturn(customers);

		mockMvc.perform(get("/api/v1/customers")
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
		joe.setCustomerUrl("/api/v1/customers" + joe.getId());

		when(customerService.getCustomerById(anyLong())).thenReturn(joe);

		mockMvc.perform(get("/api/v1/customers/1")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id", equalTo(1)))
			.andExpect(jsonPath("$.firstName", equalTo(joe.getFirstName())))
			.andExpect(jsonPath("$.lastName", equalTo(joe.getLastName())))
			.andExpect(jsonPath("$.customerUrl", equalTo(joe.getCustomerUrl())));
	}

	@Test
	public void createNewCustomer() throws Exception {
		//given
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName("Fred");
		customer.setLastName("Flintstone");

		CustomerDTO returnDTO = new CustomerDTO();
		returnDTO.setFirstName(customer.getFirstName());
		returnDTO.setLastName(customer.getLastName());
		returnDTO.setCustomerUrl("/api/v1/customers/1");

		when(customerService.createCustomer(customer)).thenReturn(returnDTO);

		//when/then
		mockMvc.perform(post("/api/v1/customers/")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(customer)))
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.firstName", equalTo(customer.getFirstName())))
			.andExpect(jsonPath("$.lastName", equalTo(customer.getLastName())))
			.andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customers/1")));
	}
}