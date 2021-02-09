package com.microservice.productreview.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

	String productId;
	Double averageReviewScore;
	Integer numberOfReviews;
}