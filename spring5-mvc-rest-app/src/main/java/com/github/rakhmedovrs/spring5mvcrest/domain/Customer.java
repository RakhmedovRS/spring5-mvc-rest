package com.github.rakhmedovrs.spring5mvcrest.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
@Data
@Entity
public class Customer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String firstName;
	String lastName;
	@Transient
	String customerUrl;
}
