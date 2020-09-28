package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.controllers.v1.CustomerController;
import com.github.rakhmedovrs.spring5mvcrest.domain.Customer;
import com.github.rakhmedovrs.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
class CustomerServiceTest
{
	public static final long ID = 1L;
	public static final String FIRST_NAME = "Ivan";
	public static final String LAST_NAME = "Ivanov";
	public static final String CUSTOMER_URL = CustomerController.BASE_URL + "/" + "1";
	CustomerService customerService;

	@Mock
	CustomerRepository customerRepository;

	@BeforeEach
	public void setUp() throws Exception
	{
		MockitoAnnotations.initMocks(this);

		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	@Test
	public void getAllCustomers() throws Exception
	{
		//given
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

		when(customerRepository.findAll()).thenReturn(customers);

		//when
		List<CustomerDTO> categoryDTOS = customerService.getAllCustomers();

		//then
		assertEquals(3, categoryDTOS.size());
	}

	@Test
	public void getCustomerById() throws Exception
	{
		//given
		Customer customer = new Customer();
		customer.setId(ID);
		customer.setFirstName(FIRST_NAME);
		customer.setLastName(LAST_NAME);
		customer.setCustomerUrl(CUSTOMER_URL);

		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));

		//when
		CustomerDTO customerDTO = customerService.getCustomerById(ID);

		//then
		assertEquals(ID, customerDTO.getId());
		assertEquals(FIRST_NAME, customerDTO.getFirstName());
		assertEquals(LAST_NAME, customerDTO.getLastName());
		assertEquals(CUSTOMER_URL, customerDTO.getCustomerUrl());
	}

	@Test
	public void createNewCustomer()
	{
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Hank");
		customerDTO.setLastName("Shrader");

		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		CustomerDTO savedDTO = customerService.createCustomer(customerDTO);

		assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
		assertEquals(CustomerController.BASE_URL + "/" + "1", savedDTO.getCustomerUrl());
	}

	@Test
	public void saveCustomer()
	{
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName("Hank");
		customerDTO.setLastName("Shrader");

		Customer savedCustomer = new Customer();
		savedCustomer.setFirstName(customerDTO.getFirstName());
		savedCustomer.setLastName(customerDTO.getLastName());
		savedCustomer.setId(1L);

		when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

		CustomerDTO savedDTO = customerService.saveCustomerByDTO(1L, customerDTO);

		assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
		assertEquals(CustomerController.BASE_URL + "/" + "1", savedDTO.getCustomerUrl());
	}

	@Test
	public void deleteCustomerById()
	{
		customerRepository.deleteById(1L);
		verify(customerRepository, times(1)).deleteById(anyLong());
	}
}