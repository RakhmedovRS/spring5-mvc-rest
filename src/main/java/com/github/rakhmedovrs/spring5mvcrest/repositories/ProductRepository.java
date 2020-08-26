package com.github.rakhmedovrs.spring5mvcrest.repositories;

import com.github.rakhmedovrs.spring5mvcrest.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
public interface ProductRepository extends JpaRepository<Product, Long>
{
}
