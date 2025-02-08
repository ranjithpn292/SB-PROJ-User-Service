package com.ran.user.service.external.service;


import com.ran.user.service.entities.Hotel;
import com.ran.user.service.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {


    @PostMapping("/ratings")
    Rating creatingRating(Rating rating);

    @PutMapping("/ratings/{ratingId}")
    Rating updateRating(@PathVariable String ratingId, Rating rating);

    @DeleteMapping("/ratings/{ratingId}")
    Rating deleteRating(@PathVariable String ratingId);
}
