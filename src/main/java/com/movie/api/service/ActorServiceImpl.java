package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.Constants;
import com.movie.api.exception.ActorNotFoundException;
import com.movie.api.exception.MyMovieErrorHandler;
import com.movie.api.exception.MyPersonErrorHandler;
import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import lombok.extern.slf4j.Slf4j;
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
public class ActorServiceImpl implements ActorService {

    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;
    @Override
    public Actor getActor(Long actorId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyPersonErrorHandler());
        String url = apiUrl + Constants.ENDPOINT_PERSON + "/" + actorId + "?api_key=" + apiKey;
        Actor response = restTemplate.getForObject(url, Actor.class);
        assert response != null;
        if (response.getKnown_for_department().equals(Constants.KNOWN_FOR_ACTING)) {
            return response;
        }else{
            throw new ActorNotFoundException();
        }
    }

    @Override
    public List<Actor> getActorsByMovieId(Long movieId) {
        String url = apiUrl + Constants.ENDPOINT_MOVIE + "/" + movieId + "/"+Constants.ENDPOINT_CREDITS+"?api_key=" + apiKey;
        return returnActorListFromUrl(url, Constants.ENDPOINT_MOVIE);
    }

    @Override
    public List<Actor> getPopularActors() {
        String url = apiUrl + Constants.ENDPOINT_PERSON + "/"+ Constants.ENDPOINT_POPULAR+"?api_key=" + apiKey;
        return returnActorListFromUrl(url, Constants.ENDPOINT_POPULAR);
    }

    private List<Actor> returnActorListFromUrl(String url, String type){
        List<Actor> actors = new ArrayList<Actor>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        List values = null;
        switch(type){
            case Constants.ENDPOINT_MOVIE -> values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_CAST);
            case  Constants.ENDPOINT_POPULAR -> values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_RESULTS);
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
