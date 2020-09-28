package com.github.rakhmedovrs.spring5mvcrest.repositories;

import com.github.rakhmedovrs.spring5mvcrest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
public interface VendorRepository extends JpaRepository<Vendor, Long>
{
}
