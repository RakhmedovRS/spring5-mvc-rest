package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	private String name;

	@JsonProperty("vendor_url")
	private String vendorUrl;
}
