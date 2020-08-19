package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerListDTO;
import com.github.rakhmedovrs.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController
{
	private final CustomerService customerService;

	public CustomerController(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@GetMapping("{id}")
	public ResponseEntity<CustomerDTO> getCustomerByID(@PathVariable Long id)
	{
		return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<CustomerListDTO> getAllCustomers()
	{
		return new ResponseEntity<>(new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO)
	{
		return new ResponseEntity<>(customerService.createCustomer(customerDTO), HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id,
	                                                  @RequestBody CustomerDTO customerDTO)
	{
		return new ResponseEntity<>(customerService.saveCustomerByDTO(id, customerDTO), HttpStatus.OK);
	}

	@PatchMapping("{id}")
	public ResponseEntity<CustomerDTO> patchCustomer(@PathVariable Long id,
	                                                  @RequestBody CustomerDTO customerDTO)
	{
		return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id)
	{
		customerService.deleteCustomerById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
