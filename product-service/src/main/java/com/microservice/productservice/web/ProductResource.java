package com.microservice.productservice.web;

import com.microservice.productservice.service.ProductNotFoundException;
import com.microservice.productservice.service.ProductService;
import com.microservice.productservice.service.ReviewDTO;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping( path = "/products" )
public class ProductResource {

	private final ProductService service;

	@GetMapping( "/{productId}" )
	public ResponseEntity findByProductId(@PathVariable String productId) throws ProductNotFoundException {
		var product = service.findByProductId( productId );

		var serviceUrl = "http://localhost:8080/reviews/" + productId;
		var reviewDTO = new RestTemplate().getForObject( serviceUrl, ReviewDTO.class );

		var responseMap = new HashMap();
		responseMap.put( "product", product );
		responseMap.put( "reviews", reviewDTO );

		return ResponseEntity.ok( responseMap );
	}
}