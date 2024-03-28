package dev.movies.services;

import dev.movies.DTO.MovieDTO;
import dev.movies.entities.Movie;
//import dev.movies.entities.Review;
import dev.movies.repositories.CategoryRepository;
import dev.movies.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {

        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }
    public String createMovie(Movie movie){
        Optional<Movie> existingMovie = movieRepository.findByMovieName(movie.getMovieName());
        if(existingMovie.isPresent()){
            return "Movie already exist";
        }
        movieRepository.save(movie);
        return "Movie created successfully";
    }

    public List<MovieDTO> getAllMovies() {
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getCategory() != null)
                .map(movie -> new MovieDTO(
                        movie.getId(),
                        movie.getMovieName(),
                        movie.getResume(),
                        movie.getMovieDescription(),
                        movie.getEvaluation(),
                        movie.getDurationMinutes(),
                        movie.getTrailerUrl(),
                        movie.getMovieUrl(),
                        movie.getCover(),
                        movie.getImage(),
                        movie.getReleaseDate(),
                        movie.getDirector(),
                        movie.getWriters(),
                        movie.getStars(),
                        movie.getCategory().getCategoryName()
//                        movie.getReviews().stream().map(Review -> Review.getReviewerName() + " : " + Review.getReviewText()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public MovieDTO getMovieById(long id) {
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            return new MovieDTO(
                    movie.getId(),
                    movie.getMovieName(),
                    movie.getResume(),
                    movie.getMovieDescription(),
                    movie.getEvaluation(),
                    movie.getDurationMinutes(),
                    movie.getTrailerUrl(),
                    movie.getMovieUrl(),
                    movie.getCover(),
                    movie.getImage(),
                    movie.getReleaseDate(),
                    movie.getDirector(),
                    movie.getWriters(),
                    movie.getStars(),
                    movie.getCategory() != null ? movie.getCategory().getCategoryName() : null
//                    movie.getReviews().stream().map(Review -> Review.getReviewerName() + " : " + Review.getReviewText()).collect(Collectors.toList())

            );
        } else {
            return null;
        }
    }

    public MovieDTO findMovieByMovieName(String movieName){
        Optional<Movie> optionalMovie = movieRepository.findByMovieName(movieName);
        if(optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            return new MovieDTO(
                    movie.getId(),
                    movie.getMovieName(),
                    movie.getResume(),
                    movie.getMovieDescription(),
                    movie.getEvaluation(),
                    movie.getDurationMinutes(),
                    movie.getTrailerUrl(),
                    movie.getMovieUrl(),
                    movie.getCover(),
                    movie.getImage(),
                    movie.getReleaseDate(),
                    movie.getDirector(),
                    movie.getWriters(),
                    movie.getStars(),
                    movie.getCategory() != null ? movie.getCategory().getCategoryName() : null
//                    movie.getReviews().stream().map(Review -> Review.getReviewerName() + " : " + Review.getReviewText()).collect(Collectors.toList())
            );
        } else {
            return null;
        }
    }

    public List<MovieDTO> searchForMovies(String movieName) {
        List<Movie> movies = movieRepository.findByMovieNameContains(movieName);
        return movies.stream()
                .map(movie -> new MovieDTO(
                        movie.getId(),
                        movie.getMovieName(),
                        movie.getResume(),
                        movie.getMovieDescription(),
                        movie.getEvaluation(),
                        movie.getDurationMinutes(),
                        movie.getTrailerUrl(),
                        movie.getMovieUrl(),
                        movie.getCover(),
                        movie.getImage(),
                        movie.getReleaseDate(),
                        movie.getDirector(),
                        movie.getWriters(),
                        movie.getStars(),
                        movie.getCategory() != null ? movie.getCategory().getCategoryName() : null
                ))
                .collect(Collectors.toList());
    }

}
