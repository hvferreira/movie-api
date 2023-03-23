package com.movie.api.service;

import com.movie.api.exception.MyMovieErrorHandler;
import com.movie.api.model.MovieReviewResponse;
import com.movie.api.model.MovieReviews;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@PropertySource("/application.properties")
public class MovieReviewServiceImpl implements MovieReviewService {
    @Value("${apiKey}")
    private String apiKey;

    @Value("${apiUrl}")
    private String apiUrl;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(MovieReviewServiceImpl.class);

    public MovieReviewServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public List<MovieReviews> getReviewsByMovieId(Long movieId) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        String url = apiUrl + "/movie/" + movieId + "/reviews?api_key=" + apiKey;

        MovieReviewResponse response = restTemplate.getForObject(url, MovieReviewResponse.class);

        // Extract the rating value and parse the date and time strings for each review
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        for (MovieReviews review : response.getResults()) {
            if (review.getAuthor_details() != null) {
                review.setRating(review.getAuthor_details().getRating());
            }
            if (review.getCreated_at() != null) {
                CharSequence cs = new StringBuilder(String.valueOf(review.getCreated_at()));
                review.setCreated_at(LocalDateTime.parse(cs, formatter));
            }
            if (review.getUpdated_at() != null) {
                CharSequence cs = new StringBuilder(String.valueOf(review.getUpdated_at()));
                review.setUpdated_at(LocalDateTime.parse(cs, formatter));
            }
        }
        return response.getResults();

    }
}




