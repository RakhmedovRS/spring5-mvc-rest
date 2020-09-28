package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO
{
	@ApiModelProperty(name = "Vendor name", required = true)
	private String name;

	@JsonProperty("vendor_url")
	@ApiModelProperty(name = "Vendor URL")
	private String vendorUrl;
}
