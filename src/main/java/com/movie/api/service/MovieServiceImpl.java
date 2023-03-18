package com.movie.api.service;

import com.movie.api.Model.Movie;
import com.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie>movies= new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovieById(Long id) {
        return null;
    }

    @Override
    public Movie updateMovieById(Long id, Movie movie) {
        return null;
    }
}
