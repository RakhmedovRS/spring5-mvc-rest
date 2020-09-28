package com.github.rakhmedovrs.spring5mvcrest.bootstrap;

import com.github.rakhmedovrs.spring5mvcrest.domain.Customer;
import com.github.rakhmedovrs.spring5mvcrest.domain.Product;
import com.github.rakhmedovrs.spring5mvcrest.repositories.CustomerRepository;
import com.github.rakhmedovrs.spring5mvcrest.repositories.ProductRepository;
import com.github.rakhmedovrs.spring5mvcrest.domain.Category;
import com.github.rakhmedovrs.spring5mvcrest.domain.Vendor;
import com.github.rakhmedovrs.spring5mvcrest.repositories.CategoryRepository;
import com.github.rakhmedovrs.spring5mvcrest.repositories.VendorRepository;
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
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final VendorRepository vendorRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, ProductRepository productRepository, VendorRepository vendorRepository)
	{
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.vendorRepository = vendorRepository;
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

		Customer joe = new Customer();
		joe.setId(1L);
		joe.setFirstName("Joe");
		joe.setLastName("Newman");
		joe.setCustomerUrl("/api/v1/customers" + joe.getId());

		Customer jesse = new Customer();
		jesse.setId(2L);
		jesse.setFirstName("Jesse");
		jesse.setLastName("Pinkman");
		jesse.setCustomerUrl("/api/v1/customers" + jesse.getId());

		Customer walter = new Customer();
		walter.setId(3L);
		walter.setFirstName("Walter");
		walter.setLastName("White");
		walter.setCustomerUrl("/api/v1/customers" + walter.getId());

		customerRepository.save(joe);
		customerRepository.save(jesse);
		customerRepository.save(walter);

		System.out.println("Customers loaded -> " + customerRepository.count());

		Product apples = new Product();
		apples.setName("Apples");
		apples.setPrice(5D);

		Product oranges = new Product();
		oranges.setName("Oranges");
		oranges.setPrice(4D);

		Product limes = new Product();
		limes.setName("Limes");
		limes.setPrice(7D);

		Product bananas = new Product();
		bananas.setName("Bananas");
		bananas.setPrice(2D);

		productRepository.save(apples);
		productRepository.save(oranges);
		productRepository.save(limes);
		productRepository.save(bananas);

		System.out.println("Products loaded -> " + productRepository.count());

		Vendor vendor1 = new Vendor();
		vendor1.setName("Vendor 1");
		vendorRepository.save(vendor1);

		Vendor vendor2 = new Vendor();
		vendor2.setName("Vendor 2");
		vendorRepository.save(vendor2);
		System.out.println("Vendors loaded -> " + vendorRepository.count());
	}
}
