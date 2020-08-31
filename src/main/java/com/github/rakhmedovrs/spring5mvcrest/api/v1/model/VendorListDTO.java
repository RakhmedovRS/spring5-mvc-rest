package com.github.rakhmedovrs.spring5mvcrest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorListDTO
{
	List<VendorDTO> vendors;
}
