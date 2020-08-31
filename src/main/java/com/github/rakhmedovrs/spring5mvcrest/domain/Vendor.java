package com.github.rakhmedovrs.spring5mvcrest.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author RakhmedovRS
 * @created 31-Aug-20
 */
@Data
@Entity
public class Vendor
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
}
