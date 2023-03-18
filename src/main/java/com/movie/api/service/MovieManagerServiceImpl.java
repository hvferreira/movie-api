package com.movie.api.service;

import com.movie.api.model.Movie;
import com.movie.api.repository.MovieManagerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MovieManagerServiceImpl implements MovieManagerService {

    @Autowired
    MovieManagerRepository movieManagerRepository;

    @Override
    public List<Movie> getAllMovies() {
        return null;
    }

    @Override
    public Movie getMovieById(Long movieId) {
        log.debug("##### ServiceImpl *** getMovieById *** NameMovie="+movieId +" ######");
        return null;
    }
}
