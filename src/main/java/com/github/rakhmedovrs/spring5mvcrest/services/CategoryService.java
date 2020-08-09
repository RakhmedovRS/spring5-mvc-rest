package com.github.rakhmedovrs.spring5mvcrest.services;

import com.github.rakhmedovrs.spring5mvcrest.api.v1.model.CategoryDTO;

import java.util.List;

/**
 * @author RakhmedovRS
 * @created 09-Aug-20
 */
public interface CategoryService
{
	List<CategoryDTO> getAllCategories();

	CategoryDTO getCategoryByName(String name);
}
