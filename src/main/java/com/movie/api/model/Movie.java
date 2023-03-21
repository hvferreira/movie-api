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
    private Long id;
    //@Column
    private String original_title;

    private String release_date;

    private String overview;


    //@Column
    //String director;

    //@Column
    //LocalDate releaseDate;
}
