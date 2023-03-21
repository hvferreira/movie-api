package com.movie.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(updatable = false, nullable = false)
    private Long movie_id;
    //@Column
    private String original_title;


    //@Column
    //String director;

    //@Column
    //LocalDate releaseDate;
}
