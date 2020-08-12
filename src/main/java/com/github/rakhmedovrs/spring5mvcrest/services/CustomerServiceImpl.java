package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
@Service
public class CustomerServiceImpl implements CustomerService
{
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper)
	{
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public CustomerDTO getCustomerById(Long id)
	{
		return customerMapper.customerToCustomerDTO(
			customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Unable to find a Customer with id -" + id))
		);
	}

	@Override
	public List<CustomerDTO> getAllCustomers()
	{
		return customerRepository
			.findAll()
			.stream()
			.map(customerMapper::customerToCustomerDTO)
			.collect(Collectors.toList());
	}
}
