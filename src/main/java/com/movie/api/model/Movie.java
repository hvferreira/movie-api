package com.movie.api.model;

import com.movie.api.service.MovieService;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {

    @Id
    private Long id;
    private String original_title;

    private String release_date;

    private String overview;

    private double vote_average;

    private boolean adult;

    public boolean getAdult() {
        return adult;
    }
}
