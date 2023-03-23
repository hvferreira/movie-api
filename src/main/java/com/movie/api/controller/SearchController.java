package com.movie.api.controller;


import com.movie.api.Constants;
import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import com.movie.api.service.SearchService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@ToString
@RestController
@RequestMapping("/api/v1/search")
public class SearchController {
    @Autowired
    SearchService searchService;

    @GetMapping({"/"})
    public ResponseEntity<String> defaultMapping() {
        return null;
    }

    @GetMapping({"/movie/{searchTerm}"})
    public ResponseEntity<List<Movie>> searchMovies(@PathVariable String searchTerm) {
        return new ResponseEntity<List<Movie>>(searchService.searchMovies(searchTerm), HttpStatus.OK);
    }

    @GetMapping({"/actor/{searchTerm}"})
    public ResponseEntity<List<Actor>> searchActor(@PathVariable String searchTerm) {
        return new ResponseEntity<List<Actor>>(searchService.searchActor(searchTerm), HttpStatus.OK);
    }

    @GetMapping({"/keyword/{searchTerm}"})
    public ResponseEntity<List<Actor>> searchKeyword(@PathVariable String searchTerm) {
        return new ResponseEntity<List<Actor>>(searchService.searchActor(searchTerm), HttpStatus.OK);
    }

}
