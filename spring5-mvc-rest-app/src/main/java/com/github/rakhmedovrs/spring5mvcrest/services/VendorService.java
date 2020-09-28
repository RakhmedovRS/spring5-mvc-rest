package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.VendorDTO;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.VendorListDTO;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
public interface VendorService
{
	VendorDTO getVendorById(Long id);

	VendorListDTO getAllVendors();

	VendorDTO createNewVendor(VendorDTO vendorDTO);

	VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

	VendorDTO patchVendorByDTO(Long id, VendorDTO vendorDTO);

	void deleteVendorById(Long id);
}
