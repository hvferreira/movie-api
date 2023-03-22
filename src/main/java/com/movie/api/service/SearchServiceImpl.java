package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.Constants;
import com.movie.api.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
        List<Movie> movies = new ArrayList<Movie>();
        RestTemplate restTemplate = new RestTemplate();
        List values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_RESULTS);
        for (Object value : values) {
            Movie movie = mapper.convertValue(value, Movie.class);
            movies.add(movie);
        }
        return movies;
    }
}
