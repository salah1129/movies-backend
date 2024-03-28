package dev.movies.controllers;

import dev.movies.DTO.ReviewDTO;
import dev.movies.entities.Review;
import dev.movies.services.ReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewServices reviewServices;

    @PostMapping
    public String addReview(@RequestBody Review review){
        reviewServices.addReview(review);
        return "Review added successfully";
    }
    @GetMapping
    public List<ReviewDTO> getReviews(){
        return reviewServices.getAllReviews();
    }
}
