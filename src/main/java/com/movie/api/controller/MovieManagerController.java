package com.movie.api.controller;

import com.movie.api.model.Movie;
import com.movie.api.service.MovieManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/movie")
public class MovieManagerController {
    @Autowired
    MovieManagerService movieManagerService;

    @GetMapping({"/{movieId}"})
    public ResponseEntity<Movie> movieByID(@PathVariable Long movieId) {
        log.debug("##### CONTROLLER *** movieByID ######");
        return new ResponseEntity<>(movieManagerService.getMovieById(movieId), HttpStatus.OK);

    }
}
