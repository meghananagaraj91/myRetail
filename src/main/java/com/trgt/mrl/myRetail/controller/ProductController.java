package com.trgt.mrl.myRetail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trgt.mrl.myRetail.entiry.Product;
import com.trgt.mrl.myRetail.exception.CustomResponse;
import com.trgt.mrl.myRetail.exception.ProductMisMatchException;
import com.trgt.mrl.myRetail.exception.ProductNotFoundException;
import com.trgt.mrl.myRetail.service.ProductService;

/**
 * @author Rohit 
 * Created On : 10/02/2017
 */
@RequestMapping(value="/products/")
@RestController
public class ProductController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	/**
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductInfo(@PathVariable("id") String productId) {
		logger.info("Inside getproductInfo  " + productId);

		Product product = null;
		try {
			product = productService.getProductById(productId);
		} catch (Exception e) {
			logger.debug("Product Not Found Exception  " + e);
			throw new ProductNotFoundException();
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	/**
	 * @param prodInfo
	 * @param productId
	 * @return
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<CustomResponse> modifyPrice(@RequestBody Product product,
			@PathVariable("id") String productId) {
		if (!product.productId.equalsIgnoreCase(productId)) {
			throw new ProductMisMatchException();
		}
		try {
			productService.updateProductById(product);
		} catch (Exception e) {
			logger.debug("Product Not Found Exception while update " + e);
			throw new ProductNotFoundException();
		}

		return new ResponseEntity<CustomResponse>(
				new CustomResponse(HttpStatus.OK.value(), "Product price has been updated"), HttpStatus.OK);
	}
}
