package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.github.rakhmedovrs.spring5mvcrest.services.CustomerService;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerDTO;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CustomerListDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
@Api(description = "Customer Controller")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController
{
	public static final String BASE_URL = "/api/v1/customers";

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService)
	{
		this.customerService = customerService;
	}

	@ApiOperation(value = "Find customer by ID")
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO getCustomerByID(@PathVariable Long id)
	{
		return customerService.getCustomerById(id);
	}

	@ApiOperation(value = "Returns list of customers")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CustomerListDTO getAllCustomers()
	{
		return new CustomerListDTO(customerService.getAllCustomers());
	}

	@ApiOperation(value = "Create new customer")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO)
	{
		return customerService.createCustomer(customerDTO);
	}

	@ApiOperation(value = "Create or update customer by ID")
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO updateCustomer(@PathVariable Long id,
	                                  @RequestBody CustomerDTO customerDTO)
	{
		return customerService.saveCustomerByDTO(id, customerDTO);
	}

	@ApiOperation(value = "Patch update customer by ID")
	@PatchMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDTO patchCustomer(@PathVariable Long id,
	                                 @RequestBody CustomerDTO customerDTO)
	{
		return customerService.patchCustomer(id, customerDTO);
	}

	@ApiOperation(value = "Delete customer by ID")
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable Long id)
	{
		customerService.deleteCustomerById(id);
	}
}
