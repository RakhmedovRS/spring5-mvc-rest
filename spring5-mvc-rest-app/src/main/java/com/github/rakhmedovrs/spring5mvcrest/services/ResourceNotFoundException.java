package com.github.rakhmedovrs.spring5mvcrest.services;

/**
 * @author RakhmedovRS
 * @created 21-Aug-20
 */
public class ResourceNotFoundException extends RuntimeException
{
	public ResourceNotFoundException()
	{
		super();
	}

	public ResourceNotFoundException(String message)
	{
		super(message);
	}

	public ResourceNotFoundException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public ResourceNotFoundException(Throwable cause)
	{
		super(cause);
	}
}
