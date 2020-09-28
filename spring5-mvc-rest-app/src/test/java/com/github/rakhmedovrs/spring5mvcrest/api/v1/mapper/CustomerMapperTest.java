package com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
class CustomerMapperTest
{
	public static final long ID = 1L;
	public static final String FIRST_NAME = "Ivan";
	public static final String LAST_NAME = "Ivanov";
	public static final String CUSTOMER_URL = "url";

	CustomerMapper customerMapper = CustomerMapper.INSTANCE;

	@Test
	public void customerToCustomerDTO()
	{
		//given
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		customer.setCustomerUrl(CUSTOMER_URL);

		//when
		CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

		//then
		assertEquals(ID, customerDTO.getId());
		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
		assertEquals(CUSTOMER_URL, customerDTO.getCustomerUrl());
	}
}