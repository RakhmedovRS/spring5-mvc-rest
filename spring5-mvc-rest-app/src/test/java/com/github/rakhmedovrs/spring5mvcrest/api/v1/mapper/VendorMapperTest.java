package com.github.rakhmedovrs.spring5mvcrest.api.v1.mapper;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.VendorDTO;
import com.github.rakhmedovrs.spring5mvcrest.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
public class VendorMapperTest
{
	public static final String NAME = "someName";

	VendorMapper vendorMapper = VendorMapper.INSTANCE;

	@Test
	public void vendorToVendorDTO() throws Exception
	{
		//given
		Vendor vendor = new Vendor();
		vendor.setName(NAME);

		//when
		VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

		//then
		assertEquals(vendor.getName(), vendorDTO.getName());
	}

	@Test
	public void vendorDTOtoVendor() throws Exception
	{
		//given
		VendorDTO vendorDTO = new VendorDTO();
		vendorDTO.setName(NAME);

		//when
		Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

		//then
		assertEquals(vendorDTO.getName(), vendor.getName());
	}
}
