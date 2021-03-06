package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.github.rakhmedovrs.spring5mvcrest.controllers.v1.CustomerController;
import com.github.rakhmedovrs.spring5mvcrest.domain.Customer;
import com.github.rakhmedovrs.spring5mvcrest.model.CustomerDTO;
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
		Customer customer =
			customerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Unable to find a Customer with id -" + id));

		customer.setCustomerUrl(CustomerController.BASE_URL + "/" + customer.getId());

		return customerMapper.customerToCustomerDTO(customer);
	}

	@Override
	public List<CustomerDTO> getAllCustomers()
	{
		return customerRepository
			.findAll()
			.stream()
			.map(customerMapper::customerToCustomerDTO)
			.peek(customerDTO ->
				customerDTO.setCustomerUrl(CustomerController.BASE_URL + "/" + customerDTO.getId()))
			.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO customerDTO)
	{
		return saveAndReturn(customerDTO);
	}

	@Override
	public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO)
	{
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		customer.setId(id);
		return saveAndReturn(customerDTO);
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO)
	{
		return customerRepository
			.findById(id)
			.map(customer ->
			{
				customer.setFirstName(customerDTO.getFirstName() == null ? customer.getFirstName() : customerDTO.getFirstName());
				customer.setLastName(customerDTO.getLastName() == null ? customer.getLastName() : customerDTO.getLastName());
				customer.setCustomerUrl(CustomerController.BASE_URL + "/" + + id);
				return customerMapper.customerToCustomerDTO(customer);
			})
			.orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public void deleteCustomerById(Long id)
	{
		customerRepository.deleteById(id);
	}

	private CustomerDTO saveAndReturn(CustomerDTO customerDTO)
	{
		Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
		Customer saved = customerRepository.save(customer);
		saved.setCustomerUrl(CustomerController.BASE_URL + "/" + + saved.getId());
		return customerMapper.customerToCustomerDTO(saved);
	}
}