package dev.movies.DTO;
import dev.movies.entities.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserDTO {
    private long id;
    private  String username;
    private List<String> roles;
    private Collection<Long> favoriteMovies;

}
