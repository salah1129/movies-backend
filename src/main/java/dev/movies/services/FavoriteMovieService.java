package dev.movies.services;

import dev.movies.DTO.FavoriteMovieDTO;
import dev.movies.controllers.UserExeptions;
import dev.movies.controllers.UserExeptions.MovieNotFoundException;
import dev.movies.controllers.UserExeptions.UserNotFoundException;
import dev.movies.entities.Movie;
import dev.movies.entities.UserModel;
import dev.movies.repositories.MovieRepository;
import dev.movies.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoriteMovieService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public void addFavoriteMovie(long userId, FavoriteMovieDTO favoriteMovieDTO) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            // Assuming FavoriteMovieDTO contains movieId
            Optional<Movie> optionalMovie = movieRepository.findById(favoriteMovieDTO.getMovieId());
            if (optionalMovie.isPresent()) {
                Movie movie = optionalMovie.get();
                // Add movie to user's favorites
                user.getFavoriteMovies().add(movie);
                userRepository.save(user);
            } else {
                throw new MovieNotFoundException("Movie not found");
            }
        } else {
            throw new UserExeptions.UserNotFoundException("User not found");
        }
    }

    public List<FavoriteMovieDTO> getAllFavoriteMovies(long userId) {
        Optional<UserModel> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserModel user = optionalUser.get();
            // Convert user's favorite movies to DTOs
            return user.getFavoriteMovies().stream()
                    .map(movie -> new FavoriteMovieDTO(movie.getId(),
                            movie.getMovieName(),
                            movie.getImage())) // Assuming FavoriteMovieDTO has movieId and movieName
                    .collect(Collectors.toList());
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}
