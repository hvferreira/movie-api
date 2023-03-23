package com.movie.api.service;

import com.movie.api.model.Genres;
import com.movie.api.model.Movie;
import com.movie.api.model.Actor;

import java.util.List;

public interface MovieService {

    Movie getMovieById(Long movieId);

    List<Movie> getMovies(String type);

    Movie getLatestMovie();

    List<Genres> getGenreList();

    List<Movie> getMovieRecommendationsSimilar(Long movieId, String recommendations);

    List<Movie> getMoviesByActor(Long actorId);

    public Movie getRandomMovie();

    String getDirectorByMovie(Long movieId);
}
