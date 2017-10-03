package com.trgt.mrl.myRetail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohit 
 * Created On : 10/02/2017
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found. ")
public class ProductNotFoundException extends RuntimeException {

	/**
	 * SUID
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		
	}

}
