package com.microservice.productservice.service;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {

	@Autowired
	@Qualifier( "adidasServiceConsumer" )
	private AdidasServiceConsumer adidasServiceConsumer;

	public HashMap findByProductId(@PathVariable String productId) throws ProductNotFoundException {
		return adidasServiceConsumer.getProduct( productId );
	}
}