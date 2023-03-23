package com.movie.api.controller;


import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import com.movie.api.service.ActorService;
import com.movie.api.service.MovieService;
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
@RequestMapping("/api/v1/actor")
public class ActorController {
    @Autowired
    ActorService actorService;

    @GetMapping({"/"})
    public ResponseEntity<List<Actor>> defaultMapping() {
        return popularActors();
    }
    @GetMapping({"/{actorId}"})
    public ResponseEntity<Actor> actorById(@PathVariable Long actorId) {
        return new ResponseEntity<>(actorService.getActor(actorId), HttpStatus.OK);
    }

    @GetMapping({"/popular"})
    public ResponseEntity<List<Actor>> popularActors() {
        return new ResponseEntity<>(actorService.getPopularActors(), HttpStatus.OK);

    }
}
