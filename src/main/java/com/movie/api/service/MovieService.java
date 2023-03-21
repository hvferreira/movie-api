package com.movie.api.service;

import com.movie.api.model.Movie;

import java.util.List;

public interface MovieService {
    //List<Movie> getAllMovies();

    //Movie addMovie(Movie movie);
    Movie getMovieById(Long movieId);

    List<Movie> getMovies(String type);

    Object getGenreList();
    //Movie updateMovieById(Long id,Movie movie);


}
