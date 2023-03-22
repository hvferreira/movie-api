package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.Constants;
import com.movie.api.exception.ActorNotFoundException;
import com.movie.api.exception.MyPersonErrorHandler;
import com.movie.api.model.Actor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Slf4j
@Service
@PropertySource("/application.properties")
public class ActorServiceImpl implements ActorService {

    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
        return ResponseHelper.returnActorListFromUrl(url, Constants.ENDPOINT_MOVIE);
    }

    @Override
    public List<Actor> getPopularActors() {
        String url = apiUrl + Constants.ENDPOINT_PERSON + "/"+ Constants.ENDPOINT_POPULAR+"?api_key=" + apiKey;
        return ResponseHelper.returnActorListFromUrl(url, Constants.ENDPOINT_POPULAR);
    }


}
