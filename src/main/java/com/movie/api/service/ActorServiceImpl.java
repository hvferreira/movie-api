package com.movie.api.service;

import com.movie.api.exception.MyPersonErrorHandler;
import com.movie.api.model.Actor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
        }
        return null;
    }
}
