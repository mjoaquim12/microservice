package com.microservice.productreview.service;

import com.microservice.productreview.model.Review;
import com.microservice.productreview.model.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;

	@Autowired
	@Qualifier( "adidasServiceConsumer" )
	private AdidasServiceConsumer adidasServiceConsumer;

	public List<Review> findAll() {
		return repository.findAll();
	}

	public ReviewDTO find(String productId) throws ProductNotFoundException {
		adidasServiceConsumer.getProduct( productId );

		var reviews = repository.findByProductId( productId );
		var average = reviews.stream().mapToInt( Review::getScore ).average().orElse( 0.0 );

		return new ReviewDTO.ReviewDTOBuilder()
				.averageReviewScore( average )
				.numberOfReviews( reviews.size() )
				.productId( productId )
				.build();
	}

	public Review save(Review review) throws ProductNotFoundException {
		adidasServiceConsumer.getProduct( review.getProductId() );

		return repository.insert( review );
	}

	public void update(Review review) throws ProductNotFoundException {
		adidasServiceConsumer.getProduct( review.getProductId() );

		repository.save( review );
	}

	public void deleteById(String id) {
		repository.deleteById( id );
	}
}