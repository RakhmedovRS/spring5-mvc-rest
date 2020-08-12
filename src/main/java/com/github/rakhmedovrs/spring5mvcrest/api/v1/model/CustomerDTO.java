package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import lombok.Data;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
@Data
public class CustomerDTO
{
	Long id;
	String firstName;
	String lastName;
	String customerUrl;
}
