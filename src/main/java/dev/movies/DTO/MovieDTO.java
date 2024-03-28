package dev.movies.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private long id;
    private String movieName;
    private String resume;
    private String movieDescription;
    private double evaluation;
    private int durationMinutes;
    private String trailerUrl;
    private String movieUrl;
    private String cover;
    private String image;
    private String releaseDate;
    private String director;
    private List<String> writers;
    private List<String> stars;
    private String categoryName;
//    List<String> reviews;
}
