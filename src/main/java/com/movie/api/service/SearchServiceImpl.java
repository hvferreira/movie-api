package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.Constants;
import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@PropertySource("/application.properties")
public class SearchServiceImpl implements SearchService {

    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    @Override
    public List<Movie> searchMovies(String searchTerm) {
        String url = apiUrl + Constants.ENDPOINT_SEARCH+ "/"+Constants.ENDPOINT_MOVIE+"?api_key=" + apiKey + "&query=" + searchTerm;
        return ResponseHelper.returnMovieListFromUrl(url);
    }

    @Override
    public List<Actor> searchActor(String searchTerm) {
        String url = apiUrl + Constants.ENDPOINT_SEARCH+ "/"+Constants.ENDPOINT_PERSON+"?api_key=" + apiKey + "&query=" + searchTerm;
        return ResponseHelper.returnActorListFromUrl(url, "");
    }
}
