package com.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MovieReviews {


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
