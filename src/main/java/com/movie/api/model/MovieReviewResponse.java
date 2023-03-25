package com.movie.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MovieReviewResponse {
    @JsonProperty("results")
    private List<MovieReviews> results;

    public List<MovieReviews> getResults() {
        return results;
    }

}
