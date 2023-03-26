package com.movie.api.controller;

import com.movie.api.Constants;
import com.movie.api.model.Actor;
import com.movie.api.model.Director;
import com.movie.api.model.Genres;
import com.movie.api.model.Movie;
import com.movie.api.service.ActorService;
import com.movie.api.service.MovieService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@ToString
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    private static int maximumMovieId = 1101964;

    @Autowired//movie controller class has dependencies on service class and automatically injects an instance of movie service class
    MovieService movieService;

    @Autowired
    ActorService actorService;

    @GetMapping({"/"})
    public ResponseEntity<Movie> defaultMapping() {
        return latestMovie();
    }

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

    @GetMapping({"/actor"})
    public ResponseEntity<List<Movie>> moviesWithActors(@RequestParam Long actor1, @RequestParam(required = false) Long actor2) {
        if (actor2 == null) {
            return new ResponseEntity<>(movieService.getMoviesByActor(actor1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(movieService.getMoviesWithActors(actor1, actor2), HttpStatus.OK);
        }
    }

    @GetMapping({"/{movieId}/director"})
    public ResponseEntity<Director> directorByMovie(@PathVariable Long movieId) {
        return new ResponseEntity<>(movieService.getDirectorByMovie(movieId), HttpStatus.OK);

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

    @GetMapping({"/{movieId}/actors"})
    public ResponseEntity<List<Actor>> actorsByMovieId(@PathVariable Long movieId) {
        return new ResponseEntity<>(actorService.getActorsByMovieId(movieId), HttpStatus.OK);
    }


    @GetMapping({"/rating"})
    public ResponseEntity<List<Movie>> moviesWithinRating(@RequestParam Double rateMin, @RequestParam(required = false) Double rateMax) {
        return new ResponseEntity<>(movieService.getMoviesWithinRating(rateMin, rateMax), HttpStatus.OK);
    }

    @GetMapping({"/random"})
    public ResponseEntity<Movie> chooseRandomMovie() {
        log.debug("##### CONTROLLER *** random  ######");
        return new ResponseEntity<>(movieService.getRandomMovie(), HttpStatus.OK);
    }

    @GetMapping({"/findMovies"})
    public ResponseEntity<List<Movie>> findMoviesWithSpecificParameters(@RequestParam(defaultValue = "1900-01-01") String from_date,
                                                                        @RequestParam(required = false) String genre,
                                                                        @RequestParam(required = false) String rating,
                                                                        @RequestParam(required = false) String time_available) {
        LinkedHashMap<String, String> parameterMap = new LinkedHashMap<>();
        parameterMap.put("genre", genre);
        parameterMap.put("from_date", from_date);
        parameterMap.put("rating", rating);
        parameterMap.put("time_available", time_available);
        return new ResponseEntity<>(movieService.getMoviesWithParameters(parameterMap), HttpStatus.OK);
    }

    @GetMapping({"/upcoming"})
    public ResponseEntity<List<Movie>> getUpcomingMovies(@RequestParam(defaultValue = "GB") String region) {
        return new ResponseEntity<>(movieService.getUpcomingMovies(region), HttpStatus.OK);
    }

}
