package com.movie.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieReviews {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    //private String title;
    private String author;
    private AuthorDetails author_details;
    private String content;
    private double rating;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private String url;


}
