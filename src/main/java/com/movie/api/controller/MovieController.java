package com.movie.api.controller;

import com.movie.api.model.Movie;
import com.movie.api.service.MovieService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.actuate.health.Health;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ToString
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    @Autowired//movie controller class has dependencies on service class and automatically injects an instance of movie service class
    MovieService movieService;


    @GetMapping({"/{movieId}"})
    public ResponseEntity<Movie> movieByID(@PathVariable Long movieId) {
        log.debug("##### CONTROLLER *** movieByID ID=" + movieId + " ######");
        return new ResponseEntity<>(movieService.getMovieById(movieId), HttpStatus.OK);

    }


    @GetMapping({"/health"})
    public ResponseEntity<Health> health() {
        log.debug("##### CONTROLLER *** health  ######");
        return new ResponseEntity<>(Health.up().build(), HttpStatus.OK);
    }

     /*@GetMapping({"/allmovies"})
    public ResponseEntity<List<Movie>> allMovies() {
        log.debug("##### CONTROLLER *** allMovies ######");
        List<Movie> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }
*/

   /* @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        Movie newMovie = movieService.addMovie(movie);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("book", "/api/v1/book/" + newMovie.getId().toString());
        System.out.println("Movie added successfully with ID " + newMovie.getId());
        return new ResponseEntity<>(newMovie, httpHeaders, HttpStatus.CREATED);
    }*/
}
