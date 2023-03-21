package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.exception.ActorNotFoundException;
import com.movie.api.exception.MyMovieErrorHandler;
import com.movie.api.exception.MyPersonErrorHandler;
import com.movie.api.model.Actor;
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
        String url = apiUrl + "person/" + actorId + "?api_key=" + apiKey;
        Actor response = restTemplate.getForObject(url, Actor.class);
        assert response != null;
        if (response.getKnown_for_department().equals("Acting")) {
            return response;
        }else{
            throw new ActorNotFoundException();
        }
    }

    @Override
    public List<Actor> getActorsByMovieId(Long movieId) {
        List<Actor> actors = new ArrayList<Actor>();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        String url = apiUrl + "movie/" + movieId + "/credits?api_key=" + apiKey;
        List values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get("cast");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for(Object value : values){
            Actor actor = mapper.convertValue(value, Actor.class);
            if(actor.getKnown_for_department().equals("Acting")){
                actors.add(actor);
            }
        }
        return actors;
    }
}
