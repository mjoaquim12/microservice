package com.microservice.productservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdidasServiceConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger( AdidasServiceConsumer.class );

	@Autowired
	private ObjectMapper jacksonObjectMapper;

	public HashMap getProduct(String productId) throws ProductNotFoundException {
		var httpRequest = HttpRequest.newBuilder( URI.create( "https://www.adidas.co.uk/api/products/" + productId ) ).GET().build();

		try{
			var httpResponse = HttpClient.newBuilder()
					.version( Version.HTTP_2 )
					.connectTimeout( Duration.ofSeconds( 30 ) )
					.build()
					.send( httpRequest, BodyHandlers.ofString( StandardCharsets.UTF_8 ) );

			if( httpResponse.statusCode() != 200 ){
				throw new ProductNotFoundException( "Product not found with id: " + productId );
			}

			return jacksonObjectMapper.readValue( httpResponse.body(), HashMap.class );

		}catch(IOException | InterruptedException e){
			LOGGER.error( "Error while invoking adidas product api", e );
		}

		return new HashMap<>();
	}
}