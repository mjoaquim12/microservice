package com.microservice.productreview.configuration;

import com.microservice.productreview.model.Review;
import com.microservice.productreview.model.ReviewRepository;
import com.microservice.productreview.service.AdidasServiceConsumer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger( DatabaseInitializer.class );

	@Autowired
	private ReviewRepository repository;

	@PostConstruct
	public void init() {
		if( repository.findAll().isEmpty() ){
			repository.insert(
					List.of(
							Review.builder().productId( "M20324" ).score( 5 ).build(),
							Review.builder().productId( "M20324" ).score( 3 ).build(),
							Review.builder().productId( "BB5476" ).score( 3 ).build(),
							Review.builder().productId( "CA3456" ).score( 1 ).build() ) );
		}
	}
}
