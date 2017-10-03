package com.trgt.mrl.myRetail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohit 
 * Created On : 10/02/2017
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "ProductId in request header and body doesn't match.")
public class ProductMisMatchException extends RuntimeException {

	/**
	 * SUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ProductMisMatchException() {
		// TODO Auto-generated constructor stub
	}

}
