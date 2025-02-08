package com.ran.user.service;

//import com.ran.user.service.entities.Rating;
import com.ran.user.service.external.service.RatingService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@Service
@SpringBootTest
class UserServiceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(UserServiceApplicationTests.class);
	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

//	@Test
//	void createRating(){
//		Rating rating = new Rating();
//		rating.setUserId("");
//		rating.setHotelId("");
//		rating.setRating(10);
//		rating.setFeedback("this feedback is created using feign client");
//		Rating savedRating = ratingService.creatingRating(rating);
//
//		log.info("new rating created {}", savedRating);
//	}

}
