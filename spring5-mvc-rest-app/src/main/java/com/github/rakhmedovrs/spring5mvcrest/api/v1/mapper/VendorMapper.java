package com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.VendorDTO;
import com.github.rakhmedovrs.spring5mvcrest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
@Mapper
public interface VendorMapper
{
	VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

	VendorDTO vendorToVendorDTO(Vendor vendor);

	Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}
