package com.ran.user.service.services.impl;

import com.ran.user.service.entities.Hotel;
import com.ran.user.service.entities.Rating;
import com.ran.user.service.entities.User;
import com.ran.user.service.respositories.UserRepository;
import com.ran.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        // generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {

        // TODO :implement rating service call using rest template
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        // fetch user with the help of userId from user repository
        User user = userRepository.findById(userId).orElseThrow(()-> new ResolutionException("User with given Id not found"+ userId));
        // fetch ratings of the user from Rating Service with help below url
        Rating[] ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/userid/"+ user.getUserId(), Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList = (List<Rating>) ratings.stream().map((rating) ->{
            // api call to hotel service
            // http://localhost:8082/hotels/9d2b6317-f246-4a22-b6bb-d68b1b0c686f
            Hotel hotel = restTemplate.getForObject("http://localhost:8082/hotels/" + rating.getHotelId(), Hotel.class);

            log.info("hotel : {}", hotel);

            // set hotel to rating
            rating.setHotel(hotel);

            // return the rating
            return rating;
        }).collect(Collectors.toList());

        log.info("ratinngList : {}", ratingList);
        user.setRatings(ratingList);
        return user;
    }
}
