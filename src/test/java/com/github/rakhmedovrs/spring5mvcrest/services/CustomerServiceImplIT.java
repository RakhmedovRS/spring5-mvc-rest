package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.bootstrap.Bootstrap;
import com.github.rakhmedovrs.spring5mvcrest.domain.Customer;
import com.github.rakhmedovrs.spring5mvcrest.repositories.CategoryRepository;
import com.github.rakhmedovrs.spring5mvcrest.repositories.CustomerRepository;
import com.github.rakhmedovrs.spring5mvcrest.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author RakhmedovRS
 * @created 16-Aug-20
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT
{
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	CustomerService customerService;

	@BeforeEach
	void setUp() throws Exception
	{
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, productRepository);
		bootstrap.run();

		customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	@Test
	public void patchCustomerUpdateFirstName()
	{
		String newFirstName = "newFirstName";
		long id = getCustomerID();

		Customer customerBefore = customerRepository.getOne(id);
		String firstNameBefore = customerBefore.getFirstName();
		String lastNameBefore = customerBefore.getLastName();
		assertNotNull(customerBefore);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setFirstName(newFirstName);

		customerService.patchCustomer(id, customerDTO);

		Customer customerAfter = customerRepository.getOne(id);
		assertNotNull(customerAfter);
		assertEquals(newFirstName, customerAfter.getFirstName());
		assertNotEquals(customerAfter.getFirstName(), firstNameBefore);
		assertEquals(customerAfter.getLastName(), lastNameBefore);
	}

	@Test
	public void patchCustomerUpdateLastName()
	{
		String newLastName = "newLastName";
		long id = getCustomerID();

		Customer customerBefore = customerRepository.getOne(id);
		String firstNameBefore = customerBefore.getFirstName();
		String lastNameBefore = customerBefore.getLastName();
		assertNotNull(customerBefore);

		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setLastName(newLastName);

		customerService.patchCustomer(id, customerDTO);

		Customer customerAfter = customerRepository.getOne(id);
		assertNotNull(customerAfter);
		assertEquals(newLastName, customerAfter.getLastName());
		assertNotEquals(customerAfter.getLastName(), lastNameBefore);
		assertEquals(customerAfter.getFirstName(), firstNameBefore);
	}

	private Long getCustomerID()
	{
		return customerRepository.findAll().get(0).getId();
	}
}
