package com.movie.api.controller;
import com.movie.api.Constants;
import com.movie.api.model.Actor;
import com.movie.api.model.Genres;
import com.movie.api.model.Movie;
import com.movie.api.service.ActorService;
import com.movie.api.service.MovieService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@Slf4j
@ToString
@RestController
@RequestMapping("/api/v1/movie")

public class MovieController {
    @Autowired//movie controller class has dependencies on service class and automatically injects an instance of movie service class
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @GetMapping({"/"})
    public ResponseEntity<Movie> defaultMapping() {
       return latestMovie();
    }

public class MovieController {
    @Autowired//movie controller class has dependencies on service class and automatically injects an instance of movie service class
    MovieService movieService;
    private static int maximumMovieId = 1101964;


    @GetMapping({"/{movieId}/recommendations"})
    public ResponseEntity<List<Movie>> movieRecommendations(@PathVariable Long movieId) {
        log.debug("##### CONTROLLER *** MovieRecommendations ######");
        return new ResponseEntity<>(movieService.getMovieRecommendationsSimilar(movieId, Constants.ENDPOINT_RECOMMENDATIONS), HttpStatus.OK);

    }

    @GetMapping({"/{movieId}/similar"})
    public ResponseEntity<List<Movie>> movieSimilar(@PathVariable Long movieId) {
        log.debug("##### CONTROLLER *** movieSimilar ######");
        return new ResponseEntity<>(movieService.getMovieRecommendationsSimilar(movieId, Constants.ENDPOINT_SIMILAR), HttpStatus.OK);

    }

    @GetMapping({"/actor/{actorId}"})
    public ResponseEntity<List<Movie>> moviesByActor(@PathVariable Long actorId) {
        return new ResponseEntity<List<Movie>>(movieService.getMoviesByActor(actorId), HttpStatus.OK);

    }

    @GetMapping({"/{movieId}/director"})
    public ResponseEntity<String> directorByMovie(@PathVariable Long movieId) {
        return new ResponseEntity<String>(movieService.getDirectorByMovie(movieId), HttpStatus.OK);

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
        return new ResponseEntity<>(movieService.getMovies(Constants.ENDPOINT_POPULAR), HttpStatus.OK);

    }

    @GetMapping({"/topRatedMovies"})
    public ResponseEntity<List<Movie>> topRatedMovies() {
        return new ResponseEntity<>(movieService.getMovies(Constants.ENDPOINT_TOP_RATED), HttpStatus.OK);

    }

    @GetMapping({"/latestMovie"})
    public ResponseEntity<Movie> latestMovie() {
        return new ResponseEntity<>(movieService.getLatestMovie(), HttpStatus.OK);

    }

    @GetMapping({"{movieId}/actors"})
    public ResponseEntity<List<Actor>> actorsByMovieId(@PathVariable Long movieId) {
        return new ResponseEntity<>(actorService.getActorsByMovieId(movieId), HttpStatus.OK);
    }

    @GetMapping({"/random"})
    public ResponseEntity<Movie> chooseRandomMovie() {
        log.debug("##### CONTROLLER *** random  ######");
        return new ResponseEntity<>(movieService.getRandomMovie(), HttpStatus.OK);
    }

    @GetMapping({"/health"})
    public ResponseEntity<Health> health() {
        log.debug("##### CONTROLLER *** health  ######");
        return new ResponseEntity<>(Health.up().build(), HttpStatus.OK);
    }

}
