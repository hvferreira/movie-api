package com.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDetails {

    private String name;
    private String username;
    private String avatar_path;
    private Double rating;
}