package dev.movies.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class UserModel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String username;
    private  String  password;
    @ManyToMany
    private Collection<RoleModel> roles;
    @ManyToMany
    private Collection<Movie> favoriteMovies;
}
