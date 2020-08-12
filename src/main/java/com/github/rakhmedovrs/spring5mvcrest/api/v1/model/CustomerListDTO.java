package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
@Data
@AllArgsConstructor
public class CustomerListDTO
{
	List<CustomerDTO> customers;
}
