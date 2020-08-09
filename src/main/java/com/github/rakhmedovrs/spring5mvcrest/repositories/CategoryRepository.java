package com.github.rakhmedovrs.spring5mvcrest.repositories;

import com.github.rakhmedovrs.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author RakhmedovRS
 * @created 05-Aug-20
 */
public interface CategoryRepository extends JpaRepository<Category, Long>
{
	Category findByName(String name);
}
