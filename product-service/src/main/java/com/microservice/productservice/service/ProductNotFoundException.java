package com.microservice.productservice.service;

public class ProductNotFoundException extends Throwable {

	public ProductNotFoundException(String message) {
		super( message );
	}
}