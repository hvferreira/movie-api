package com.movie.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
public class MovieReviewResponse {
    @JsonProperty("results")
    private List<MovieReviews> results;
    public List<MovieReviews> getResults() {
        return results;
    }

}
