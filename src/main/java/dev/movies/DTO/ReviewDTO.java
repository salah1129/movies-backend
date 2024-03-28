package dev.movies.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private long id;
    private String reviwerName;
    private String reviewText;
    private String movieName;
    private long movieId;
}
