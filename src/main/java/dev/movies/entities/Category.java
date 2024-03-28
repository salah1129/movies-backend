package dev.movies.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category {
    @Id @GeneratedValue
    private long id;
    private String categoryName;
    private String image;
    @OneToMany(mappedBy = "category")
    private List<Movie> movies;
}
