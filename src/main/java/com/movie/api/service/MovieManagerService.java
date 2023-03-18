package com.movie.api.service;

import com.movie.api.model.Movie;

import java.util.List;

public interface MovieManagerService {
    List<Movie> getAllMovies();

    Movie getMovieById(Long movieId);
}
