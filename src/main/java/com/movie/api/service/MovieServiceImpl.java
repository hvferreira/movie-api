package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.exception.MyMovieErrorHandler;
import com.movie.api.model.Genres;
import com.movie.api.model.Movie;
import com.movie.api.model.Person;
import com.movie.api.repository.MovieRepository;
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
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    @Override
    public Movie getMovieById(Long movieId) {
        log.debug("##### ServiceImpl *** getMovieById *** MovieID=" + movieId + " ######");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        String url = apiUrl + "movie/" + movieId + "?api_key=" + apiKey;
        Movie movie = restTemplate.getForObject(url, Movie.class);
        log.debug("Movie " + movie.getId() + "  " + movie.getOriginal_title());
        return movie;
    }

    @Override
    public List<Movie> getMovies(String type) {
        String url = null;
        switch (type) {
            case "popular" -> url = apiUrl + "movie/popular?api_key=" + apiKey;
            case "top_rated" -> url = apiUrl + "movie/top_rated?api_key=" + apiKey;
        }
        return returnMovieListFromUrl(url);
    }

    @Override
    public Movie getLatestMovie() {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "movie/latest?api_key=" + apiKey;
        return restTemplate.getForObject(url, Movie.class);
    }

    @Override
    public List<Movie> getMoviesByActor(Long actorId) {
        List<Movie> movies = new ArrayList<Movie>();
        String url = apiUrl + "/person/" + actorId + "/movie_credits?api_key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        LinkedHashMap response = restTemplate.getForObject(url, LinkedHashMap.class);
        List values = (List) response.get("cast");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for (Object value : values) {
            Movie movie = mapper.convertValue(value, Movie.class);
            movies.add(movie);
        }
        return movies;
    }

    @Override
    public String getDirectorByMovie(Long movieId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "movie/"+movieId+"/credits?api_key=" + apiKey;
        List response = (List) restTemplate.getForObject(url, LinkedHashMap.class).get("crew");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        assert response != null;
        for(Object value : response){
            Person person = mapper.convertValue(value, Person.class);
            if(person.getJob()!=null && person.getJob().equals("Director")){
                return person.getName();
            }
        }
        return null;
    }


    public List<Genres> getGenreList() {
        List<Genres> genres = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/genre/movie/list?api_key=" + apiKey;
        log.debug("##### ServiceImpl *** getGenreList *** URL=" + apiUrl + " ######");
        List values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get("genres");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for (Object value : values) {
            Genres genre = mapper.convertValue(value, Genres.class);
            genres.add(genre);
        }
        log.debug("##### ServiceImpl *** getGenreList *** genre=" + genres.get(0).getId() + " ###### " + genres.get(0).getName());
        return genres;
    }

    @Override
    public List<Movie> getMovieRecommendationsSimilar(Long movieId, String type) {
        String url = null;
        switch (type) {
            case "recommendations" -> url = apiUrl + "/movie/" + movieId + "/recommendations?api_key=" + apiKey;
            case "similar" -> url = apiUrl + "/movie/" + movieId + "/similar?api_key=" + apiKey;
        }
        log.debug("##### ServiceImpl *** getMovieRecommendationsSimilar *** URL=" + apiUrl + " ######");
        List<Movie> movies = returnMovieListFromUrl(url);
        log.debug("##### ServiceImpl *** getMovieRecommendations *** Size=" + movies.size() + " ######");
        return movies;

    }



    private List<Movie> returnMovieListFromUrl(String url) {
        List<Movie> movies = new ArrayList<Movie>();
        RestTemplate restTemplate = new RestTemplate();
        List values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get("results");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        for (Object value : values) {
            Movie movie = mapper.convertValue(value, Movie.class);
            movies.add(movie);
        }
        return movies;
    }


}
