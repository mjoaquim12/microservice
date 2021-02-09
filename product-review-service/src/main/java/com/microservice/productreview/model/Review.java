package com.microservice.productreview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	@Id
	private String id;
	private Integer score;
	private String productId;
}