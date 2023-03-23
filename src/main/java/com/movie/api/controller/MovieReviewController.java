package com.movie.api.controller;

import com.movie.api.model.MovieReviews;
import com.movie.api.service.MovieReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/v1/movie")
public class MovieReviewController {
    @Autowired
    MovieReviewService reviewService;

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<MovieReviews>> getReviewsByMovieId(@PathVariable Long id) {
        List<MovieReviews> reviews = reviewService.getReviewsByMovieId(id);
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(reviews);
        }
    }
}
