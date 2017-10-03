package com.trgt.mrl.myRetail.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trgt.mrl.myRetail.entiry.Product;
import com.trgt.mrl.myRetail.repository.ProductRepository;

/**
 * @author Rohit 
 * Created On : 10/02/2017
 */
@Service
public class InitDbSeederService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 *  Default constructor.
	 */
	public InitDbSeederService() {
		// TODO Auto-generated constructor stub
	}

	/**
	 *  Populate data after initialize the application.
	 */
	@PostConstruct
	public void init() {
		if (productRepository != null) {
			Map<String, String> currency1 = new HashMap<>();
			currency1.put("value", "50");
			currency1.put("currency_code", "USD");
			Product product1 = new Product("13860428", "", currency1);
			
			Map<String, String> currency2 = new HashMap<>();
			currency2.put("value", "100.50");
			currency2.put("currency_code", "USD");
			Product product2 = new Product("16483589", "", currency2);

			Map<String, String> currency3 = new HashMap<>();
			currency3.put("value", "55");
			currency3.put("currency_code", "USD");
			Product product3 = new Product("16696652", "", currency3);
			
			Map<String, String> currency4 = new HashMap<>();
			currency4.put("value", "105.50");
			currency4.put("currency_code", "USD");
			Product product4 = new Product("15643793", "", currency4);

			// delete previous data
			this.productRepository.deleteAll();
			
			//Add product List in db.
			List<Product> products = Arrays.asList(product1, product2, product3, product4);
			this.productRepository.save(products);
		}
	}
}
