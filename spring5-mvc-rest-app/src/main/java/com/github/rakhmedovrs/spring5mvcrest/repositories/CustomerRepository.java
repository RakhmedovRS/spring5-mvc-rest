package com.github.rakhmedovrs.spring5mvcrest.repositories;

import com.github.rakhmedovrs.spring5mvcrest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
}
