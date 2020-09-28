package com.github.rakhmedovrs.spring5mvcrest.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 24-Aug-20
 */
@Data
@Entity
public class Product
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private Double price;
	@Transient
	private String categoryURL;
	@Transient
	private String vendorURL;

}
