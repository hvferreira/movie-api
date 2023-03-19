package com.movie.api.service;

import com.movie.api.model.Movie;

public interface MovieService {
    //List<Movie> getAllMovies();

    //Movie addMovie(Movie movie);
    Movie getMovieById(Long movieId);
    //Movie updateMovieById(Long id,Movie movie);
}
