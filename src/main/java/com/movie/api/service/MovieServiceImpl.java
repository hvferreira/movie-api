package com.movie.api.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.Constants;
import com.movie.api.exception.MovieNotFoundException;
import com.movie.api.exception.MyMovieErrorHandler;
import com.movie.api.model.Director;
import com.movie.api.model.Genres;
import com.movie.api.model.Movie;
import com.movie.api.model.Person;
import com.movie.api.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@PropertySource("/application.properties")
public class MovieServiceImpl implements MovieService {
    private static final int MAX_REQUEST = 3;
    private static int maximumMovieId = 1101964;
    @Autowired
    MovieRepository movieRepository;

    @Value("${apiKey}")
    private String apiKey;
    @Value("${apiUrl}")
    private String apiUrl;

    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public Movie getMovieById(Long movieId) {
        log.debug("##### ServiceImpl *** getMovieById *** MovieID=" + movieId + " ######");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        String url = apiUrl + Constants.ENDPOINT_MOVIE + "/" + movieId + "?api_key=" + apiKey;
        Movie movie = restTemplate.getForObject(url, Movie.class);
        log.debug("Movie " + movie.getId() + "  " + movie.getOriginal_title());
        return movie;
    }

    @Override
    public List<Movie> getMovies(String type) {
        String url = null;
        switch (type) {
            case Constants.ENDPOINT_POPULAR ->
                    url = apiUrl + Constants.ENDPOINT_MOVIE + "/" + Constants.ENDPOINT_POPULAR + "?api_key=" + apiKey;
            case Constants.ENDPOINT_TOP_RATED ->
                    url = apiUrl + Constants.ENDPOINT_MOVIE + "/" + Constants.ENDPOINT_TOP_RATED + "?api_key=" + apiKey;
        }
        return ResponseHelper.returnMovieListFromUrl(url, Constants.QUERY_RESULTS);
    }

    @Override
    public Movie getLatestMovie() {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + Constants.ENDPOINT_MOVIE + "/" + Constants.ENDPOINT_LATEST + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, Movie.class);
    }

    @Override
    public Director getDirectorByMovie(Long movieId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        String url = apiUrl + Constants.ENDPOINT_MOVIE + "/" + movieId + "/" + Constants.ENDPOINT_CREDITS + "?api_key=" + apiKey;
        List response = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_CREW);
        assert response != null;
        for (Object value : response) {
            Director director = mapper.convertValue(value, Director.class);
            if (director.getJob() != null && director.getJob().equals(Constants.JOB_DIRECTING)) {
                return director;
            }
        }
        return null;
    }

    @Override
    public List<Movie> getMoviesWithinRating(Double minRate, Double maxRate) {
        String url = apiUrl + "/" + Constants.ENDPOINT_DISCOVER + "/" + Constants.ENDPOINT_MOVIE + "?api_key=" + apiKey +
                "&vote_average.gte=" + minRate + "&vote_average.lte=" + maxRate;
        return ResponseHelper.returnMovieListFromUrl(url, Constants.QUERY_RESULTS);
    }

    @Override
    public List<Movie> getMoviesWithActors(Long actor1, Long actor2) {
        List<Movie> actor1List = getMoviesByActor(actor1);
        List<Movie> actor2List = getMoviesByActor(actor2);
        return actor1List.stream().filter(two -> actor2List.stream().anyMatch(one -> one.getId().equals(two.getId()))).collect(Collectors.toList());
    }

    @Override
    public List<Movie> getMoviesWithParameters(LinkedHashMap<String, String> parameterMap) {
        String url = apiUrl + "/" + Constants.ENDPOINT_DISCOVER + "/" + Constants.ENDPOINT_MOVIE + "?api_key=" + apiKey;
        if (parameterMap.get("genre") != null) {
            url += "&with_genres=" + parameterMap.get("genre");
        }
        if (parameterMap.get("from_date") != null) {
            url += "&release_date.gte=" + parameterMap.get("from_date");
        }
        if (parameterMap.get("rating") != null) {
            url += "&vote_average.gte=" + parameterMap.get("rating");
        }
        if (parameterMap.get("time_available") != null) {
            url += "&with_runtime.lte=" + parameterMap.get("time_available");
        }
        return ResponseHelper.returnMovieListFromUrl(url, Constants.QUERY_RESULTS);
    }


    public List<Genres> getGenreList() {
        List<Genres> genres = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/" + Constants.ENDPOINT_GENRE + "/" + Constants.ENDPOINT_MOVIE + "/list?api_key=" + apiKey;
        log.debug("##### ServiceImpl *** getGenreList *** URL=" + apiUrl + " ######");
        List values = (List) restTemplate.getForObject(url, LinkedHashMap.class).get(Constants.QUERY_GENRE);
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
            case Constants.ENDPOINT_RECOMMENDATIONS ->
                    url = apiUrl + "/" + Constants.ENDPOINT_MOVIE + "/" + movieId + "/" + Constants.ENDPOINT_RECOMMENDATIONS + "?api_key=" + apiKey;
            case Constants.ENDPOINT_SIMILAR ->
                    url = apiUrl + "/" + Constants.ENDPOINT_MOVIE + "/" + movieId + "/" + Constants.ENDPOINT_SIMILAR + "?api_key=" + apiKey;
        }
        log.debug("##### ServiceImpl *** getMovieRecommendationsSimilar *** URL=" + apiUrl + " ######");
        List<Movie> movies = ResponseHelper.returnMovieListFromUrl(url, Constants.QUERY_RESULTS);
        log.debug("##### ServiceImpl *** getMovieRecommendations *** Size=" + movies.size() + " ######");
        return movies;
    }

    @Override
    public List<Movie> getMoviesByActor(Long actorId) {
        String url = apiUrl + "/" + Constants.ENDPOINT_PERSON + "/" + actorId + "/" + Constants.ENDPOINT_CREDITS_PERSON + "?api_key=" + apiKey;
        return ResponseHelper.returnMovieListFromUrl(url, Constants.QUERY_CAST);
    }


    @Override
    public Movie getRandomMovie() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyMovieErrorHandler());
        int count = 0;
        int maxTries = 5;
        while (true) {
            try {
                Random rand = new Random();
                long id = rand.nextInt(maximumMovieId) + 1;
                String url = apiUrl + "movie/" + (id - count) + "?api_key=" + apiKey;
                Movie movie = restTemplate.getForObject(url, Movie.class);
                log.debug("Movie " + movie.getId() + "  " + movie.getOriginal_title() + " count = " + count);
                if (movie.getAdult() == false)
                    return movie;
            } catch (MovieNotFoundException e) {
                if (++count == maxTries) throw e;
            }
        }
    }
    @Override
    public List<Movie>   getUpcomingMovies(String region) {
        String url = apiUrl + "/"+Constants.ENDPOINT_MOVIE+"/" + Constants.ENDPOINT_UPCOMING + "?api_key=" + apiKey + "&region=" + region;
        return ResponseHelper.returnMovieListFromUrl(url, Constants.QUERY_RESULTS);
    }


}
