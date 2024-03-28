package dev.movies.controllers;
import dev.movies.DTO.MovieDTO;
import dev.movies.entities.Movie;
import dev.movies.repositories.MovieRepository;
import dev.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/movies")
    public String createMovie(@RequestBody Movie movie){
        Optional<Movie> existingMovie = movieRepository.findByMovieName(movie.getMovieName());
        if(existingMovie.isPresent()){
            return "Movie already exist";
        }
        movieService.createMovie(movie);
        return "Movie created successfully";
    }
    @GetMapping("/movies")
    public List<MovieDTO> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping(path = "/movies/{id}")
    public MovieDTO getMovieById(@PathVariable long id){
        return movieService.getMovieById(id);
    }

    @GetMapping(path = "/findMovieByName/{movieName}")
    public MovieDTO getMovieByMovieName(@PathVariable String movieName){
        return movieService.findMovieByMovieName(movieName);
    }

    @GetMapping(path = "/searchForMovies/{movieName}")
    public List<MovieDTO> searchForMovies(@PathVariable String movieName){
        return movieService.searchForMovies(movieName);
    }
}
