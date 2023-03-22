package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.Constants;
import com.movie.api.exception.MyMovieErrorHandler;
import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Slf4j
@Service
public class ResponseHelper {

    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static List<Movie> returnMovieListFromUrl(String url) {
        List<Movie> movies = new ArrayList<Movie>();
        RestTemplate restTemplate = new RestTemplate();
        List values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_CAST);
        for (Object value : values) {
            Movie movie = mapper.convertValue(value, Movie.class);
            movies.add(movie);
        }
        return movies;
    }

    public static List<Actor> returnActorListFromUrl(String url, String type){
        List<Actor> actors = new ArrayList<Actor>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        List values = null;
        switch(type){
            case Constants.ENDPOINT_MOVIE -> values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_CAST);
            case  Constants.ENDPOINT_POPULAR -> values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_RESULTS);
        }
        assert values != null;
        for(Object value : values){
            Actor actor = mapper.convertValue(value, Actor.class);
            if(actor.getKnown_for_department().equals(Constants.KNOWN_FOR_ACTING)){
                actors.add(actor);
            }
        }
        return actors;
    }
}
