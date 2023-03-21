package com.movie.api.controller;


import com.movie.api.model.Actor;
import com.movie.api.model.Genres;
import com.movie.api.model.Movie;
import com.movie.api.service.MovieService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@ToString
@RestController
@RequestMapping("/api/v1/movie")

public class MovieController {
    @Autowired//movie controller class has dependencies on service class and automatically injects an instance of movie service class
    MovieService movieService;

    @GetMapping({"/"})
    public ResponseEntity<Movie> defaultMapping() {
       return latestMovie();
    }

    @GetMapping({"/{movieId}/recommendations"})
    public ResponseEntity<List<Movie>> movieRecommendations(@PathVariable Long movieId) {
        log.debug("##### CONTROLLER *** MovieRecommendations ######");
        return new ResponseEntity<>(movieService.getMovieRecommendationsSimilar(movieId, "recommendations"), HttpStatus.OK);

    }

    @GetMapping({"/movie/{movieId}/similar"})
    public ResponseEntity<List<Movie>> movieSimilar(@PathVariable Long movieId) {
        log.debug("##### CONTROLLER *** movieSimilar ######");
        return new ResponseEntity<>(movieService.getMovieRecommendationsSimilar(movieId, "similar"), HttpStatus.OK);

    }

    @GetMapping({"/genrelist"})
    public ResponseEntity<List<Genres>> genrelist() {
        log.debug("##### CONTROLLER *** GenreList ######");
        return new ResponseEntity<>(movieService.getGenreList(), HttpStatus.OK);

    }


    @GetMapping({"/{movieId}"})
    public ResponseEntity<Movie> movieByID(@PathVariable Long movieId) {
        log.debug("##### CONTROLLER *** movieByID ID=" + movieId + " ######");
        return new ResponseEntity<>(movieService.getMovieById(movieId), HttpStatus.OK);

    }

    @GetMapping({"/popularMovies"})
    public ResponseEntity<List<Movie>> popularMovies() {
        return new ResponseEntity<>(movieService.getMovies("popular"), HttpStatus.OK);

    }

    @GetMapping({"/topRatedMovies"})
    public ResponseEntity<List<Movie>> topRatedMovies() {
        return new ResponseEntity<>(movieService.getMovies("top_rated"), HttpStatus.OK);

    }

    @GetMapping({"/latestMovies"})
    public ResponseEntity<Movie> latestMovie() {
        return new ResponseEntity<>(movieService.getLatestMovie(), HttpStatus.OK);

    }

    @GetMapping({"/actor/{actorId}"})
    public ResponseEntity<Actor> actorById(@PathVariable Long actorId) {
        return new ResponseEntity<>(movieService.getActor(actorId), HttpStatus.OK);

    }


    @GetMapping({"/health"})
    public ResponseEntity<Health> health() {
        log.debug("##### CONTROLLER *** health  ######");
        return new ResponseEntity<>(Health.up().build(), HttpStatus.OK);
    }

}
