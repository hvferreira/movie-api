package com.movie.api.service;

import com.movie.api.model.Movie;
import com.movie.api.repository.MovieManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieManagerServiceImpl implements MovieManagerService{

    @Autowired
    MovieManagerRepository movieManagerRepository;
    @Override
    public List<Movie> getAllMovies() {
        return null;
    }
}
