package com.movie.api.service;

import com.movie.api.model.Movie;
import com.movie.api.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;

    /*@Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }*/

    //@Override
    //public Movie addMovie(Movie movie) {
    //  return movieRepository.save(movie);
    //}

    @Override
    public Movie getMovieById(Long movieId) {
        log.debug("##### ServiceImpl *** getMovieById *** MovieID=" + movieId + " ######");
        RestTemplate restTemplate = new RestTemplate();
        Movie movie = restTemplate.getForObject("http://api.themoviedb.org/3/movie/"+movieId+"?api_key=513ec57012d6183655f825870b006514", Movie.class);
        log.debug("Movie " + movie.getMovie_id() + "  " + movie.getOriginal_title());
        return movie;
    }

    //@Override
    //public Movie updateMovieById(Long id, Movie movie) {
    //  return null;
    //}
}
