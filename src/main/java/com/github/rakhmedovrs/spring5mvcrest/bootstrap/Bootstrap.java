package com.github.rakhmedovrs.spring5mvcrest.bootstrap;

import com.github.rakhmedovrs.spring5mvcrest.domain.Category;
import com.github.rakhmedovrs.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 06-Aug-20
 */
@Component
public class Bootstrap implements CommandLineRunner
{
	private final CategoryRepository categoryRepository;

	public Bootstrap(CategoryRepository categoryRepository)
	{
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception
	{
		Category fruits = new Category();
		fruits.setName("Fruits");

		Category dried = new Category();
		dried.setName("Dried");

		Category fresh = new Category();
		fresh.setName("Fresh");

		Category exotic = new Category();
		exotic.setName("Exotic");

		Category nuts = new Category();
		nuts.setName("Nuts");

		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);

		System.out.println("Categories loaded -> " + categoryRepository.count());
	}
}
