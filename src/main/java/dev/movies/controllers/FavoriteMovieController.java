package dev.movies.controllers;

import dev.movies.DTO.FavoriteMovieDTO;
import dev.movies.services.FavoriteMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/favorites/{userId}")
public class FavoriteMovieController {

    @Autowired
    private FavoriteMovieService favoriteMovieService;

    @PostMapping
    public ResponseEntity<String> addFavoriteMovie(@PathVariable long userId, @RequestBody FavoriteMovieDTO favoriteMovieDTO) {
        favoriteMovieService.addFavoriteMovie(userId, favoriteMovieDTO);
        return ResponseEntity.ok("Favorite movie added successfully");
    }

    @GetMapping
    public ResponseEntity<List<FavoriteMovieDTO>> getAllFavoriteMovies(@PathVariable long userId) {
        List<FavoriteMovieDTO> favoriteMovies = favoriteMovieService.getAllFavoriteMovies(userId);
        return ResponseEntity.ok(favoriteMovies);
    }
}