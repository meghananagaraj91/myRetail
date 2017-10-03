package com.trgt.mrl.myRetail.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trgt.mrl.myRetail.entiry.Product;
import com.trgt.mrl.myRetail.remoteApiComm.feignClient.ProductInfoClient;
import com.trgt.mrl.myRetail.repository.ProductRepository;

/**
 * @author Rohit 
 * Created On : 10/02/2017
 */
@Service
public class ProductService {
	
	@Autowired
	private ProductInfoClient productInfoClient;
	
	@Autowired
	private ProductRepository productRepository;
	
	public ProductService() {

	}
	
	/**
	 * @param productId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public Product getProductById(String productId) throws JsonParseException, JsonMappingException, IOException {
		// From application DB
		System.out.println("productService  " + productRepository);
		Product product = productRepository.getProductByproductId(productId);
		// From external API
		product.setTitle(this.getTitleForProduct(productId));
		return product;
	}
	
	/**
	 * @param productId
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 * @throws Exception
	 * 
	 * get the title from 
	 */
	@SuppressWarnings({"unchecked","rawtypes"}) 
	private String getTitleForProduct(String productId) throws JsonParseException, JsonMappingException, IOException {
		Map<String, Map> infoMap = getProductInfoFromProductInfoService(productId);

		Map<String,Map> productMap = infoMap.get("product");
        Map<String,Map> itemMap = productMap.get("item");
        Map<String,String> prodDescrMap = itemMap.get(("product_description"));
        
        return prodDescrMap.get("title");
	}
	
	/**
	 * @param productId
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * 
	 * Getting remote data using Feign product service.
	 */
	@SuppressWarnings({"unchecked","rawtypes"})
	private Map<String, Map> getProductInfoFromProductInfoService(String productId) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper infoMapper = new ObjectMapper();
		System.out.println(productInfoClient);
		ResponseEntity<String> response = productInfoClient.getProductInfoById(productId);
		Map<String, Map> infoMap = infoMapper.readValue(response.getBody(), Map.class);
		
		return infoMap;
	}

	/**
	 * @param product
	 */
	public void updateProductById(Product product) {
		productRepository.save(product);
	}

}
