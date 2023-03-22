package com.movie.api.service;

import com.movie.api.model.Actor;
import com.movie.api.model.Movie;

import java.util.List;

public interface SearchService {
    List<Movie> searchMovies(String searchTerm);

    List<Actor> searchActor(String searchTerm);
}
