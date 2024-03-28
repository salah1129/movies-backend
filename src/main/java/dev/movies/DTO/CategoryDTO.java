package dev.movies.DTO;

import dev.movies.entities.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private long id;
    private String categoryName;
    private String image;
    private List<String> movieNames;
}
