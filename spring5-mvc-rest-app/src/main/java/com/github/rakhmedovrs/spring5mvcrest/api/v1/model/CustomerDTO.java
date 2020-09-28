package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
@Data
public class CustomerDTO
{
	@ApiModelProperty(required = true)
	Long id;

	@ApiModelProperty(value = "First Name", required = true)
	String firstName;

	@ApiModelProperty(value = "Last Name", required = true)
	String lastName;

	@ApiModelProperty(value = "Customer URL")
	String customerUrl;
}
