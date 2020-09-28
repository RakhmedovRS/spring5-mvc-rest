package com.github.rakhmedovrs.spring5mvcrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author RakhmedovRS
 * @created 12-Aug-20
 */
public class AbstractRestControllerTest
{
	public static String asJsonString(final Object object)
	{
		try
		{
			return new ObjectMapper().writeValueAsString(object);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
