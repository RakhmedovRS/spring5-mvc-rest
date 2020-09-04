package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.VendorDTO;
import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.VendorListDTO;
import com.github.rakhmedovrs.spring5mvcrest.services.VendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
@Api(description = "Vendor Controller")
@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController
{
	public static final String BASE_URL = "/api/v1/vendors";

	private final VendorService vendorService;

	public VendorController(VendorService vendorService)
	{
		this.vendorService = vendorService;
	}

	@ApiOperation(value = "Returns all vendors")
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public VendorListDTO getVendorList()
	{
		return vendorService.getAllVendors();
	}

	@ApiOperation(value = "Find vendor by ID")
	@GetMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO getVendorById(@PathVariable Long id)
	{
		return vendorService.getVendorById(id);
	}

	@ApiOperation(value = "Create new vendor")
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO)
	{
		return vendorService.createNewVendor(vendorDTO);
	}

	@ApiOperation(value = "Create or update vendor")
	@PutMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO)
	{
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}

	@ApiOperation(value = "Patch vendor")
	@PatchMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO)
	{
		return vendorService.saveVendorByDTO(id, vendorDTO);
	}

	@ApiOperation(value = "Delete vendor by ID")
	@DeleteMapping({"/{id}"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteVendor(@PathVariable Long id)
	{
		vendorService.deleteVendorById(id);
	}
}
