package com.movie.api.service;

import com.movie.api.model.Director;
import com.movie.api.model.Genres;
import com.movie.api.model.Movie;

import java.util.LinkedHashMap;
import java.util.List;

public interface MovieService {

    Movie getMovieById(Long movieId);

    List<Movie> getMovies(String type);

    Movie getLatestMovie();

    List<Genres> getGenreList();

    List<Movie> getMovieRecommendationsSimilar(Long movieId, String recommendations);

    List<Movie> getMoviesByActor(Long actorId);

    Movie getRandomMovie();

    Director getDirectorByMovie(Long movieId);

    List<Movie> getMoviesWithinRating(Double minRate, Double maxRate);

    List<Movie> getMoviesWithActors(Long actor1, Long actor2);

    List<Movie> getMoviesWithParameters(LinkedHashMap<String, String> parameterMap);
}
