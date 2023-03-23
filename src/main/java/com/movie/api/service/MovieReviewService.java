package com.movie.api.service;

import com.movie.api.model.MovieReviews;

import java.util.List;

public interface MovieReviewService {
    List<MovieReviews> getReviewsByMovieId(Long movieId);
}
