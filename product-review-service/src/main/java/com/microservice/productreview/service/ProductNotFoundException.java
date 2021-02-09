package com.microservice.productreview.service;

public class ProductNotFoundException extends Throwable {

	public ProductNotFoundException(String message) {
		super( message );
	}
}