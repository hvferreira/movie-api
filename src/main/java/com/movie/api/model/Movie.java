package com.movie.api.model;

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
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(updatable = false, nullable = false)
    private Long id;
    //@Column
    private String original_title;

    private String release_date;

    private String overview;

    private double vote_average;



    //private Genres genre;


    //@Column
    //String director;

    //@Column
    //LocalDate releaseDate;
}
