package com.github.rakhmedovrs.spring5mvcrest.services;


import com.github.rakhmedovrs.spring5mvcrest.model.CustomerDTO;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
public interface CustomerService
{
	CustomerDTO getCustomerById(Long id);

	List<CustomerDTO> getAllCustomers();

	CustomerDTO createCustomer(CustomerDTO customerDTO);

	CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

	CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);

	void deleteCustomerById(Long id);
}
