package dev.movies.services;
import dev.movies.DTO.ReviewDTO;
import dev.movies.entities.Review;
import dev.movies.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServices {
    @Autowired
    private ReviewRepository reviewRepository;

    public String addReview(Review review){
        reviewRepository.save(review);
        return "Review added succussfully";
    }

    public List<ReviewDTO> getAllReviews(){
        return reviewRepository.findAll()
                .stream()
                .filter(review -> review.getMovie() != null)
                .map(review -> new ReviewDTO(
                        review.getId(),
                        review.getReviewerName(),
                        review.getReviewText(),
                        review.getMovie().getMovieName(),
                        review.getMovie().getId()
                ))
                .collect(Collectors.toList());
    }
}
