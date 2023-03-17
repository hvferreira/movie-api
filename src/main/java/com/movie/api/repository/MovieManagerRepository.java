package com.movie.api.repository;

import com.movie.api.model.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieManagerRepository extends CrudRepository<Movie, Long> {
}
