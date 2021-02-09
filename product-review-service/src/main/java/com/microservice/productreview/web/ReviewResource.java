package com.microservice.productreview.web;

import com.microservice.productreview.model.Review;
import com.microservice.productreview.service.ProductNotFoundException;
import com.microservice.productreview.service.ReviewDTO;
import com.microservice.productreview.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/reviews" )
public class ReviewResource {

	private final ReviewService service;

	@GetMapping
	public List<Review> findAll() {
		return service.findAll();
	}

	@GetMapping( value = "/{productId}" )
	public ReviewDTO getReviews(@PathVariable( "productId" ) String productId) throws ProductNotFoundException {
		return service.find( productId );
	}

	@PostMapping
	@ResponseStatus( HttpStatus.CREATED )
	public Review newReview(@RequestBody Review review) throws ProductNotFoundException {
		return service.save( review );
	}

	@PutMapping
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void replaceReview(@RequestBody Review review) throws ProductNotFoundException {
		service.update( review );
	}

	@DeleteMapping( "/{id}" )
	void deleteReview(@PathVariable String id) {
		service.deleteById( id );
	}
}