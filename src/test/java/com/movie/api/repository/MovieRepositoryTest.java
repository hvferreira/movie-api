package com.movie.api.repository;

import com.movie.api.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.awt.print.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryTest {


    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testFindAllMovies() {

        Movie movie = new Movie(20L, "My Life Without Me", "2003-03-07", "A fatally ill mother with only two months to live creates a list of things she wants to do before she dies without telling her family of her illness.", 5.879);
        movieRepository.save(movie);

        Iterable<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(1);

    }

    @Test
    public void testCreatesAndFindMovieById() {

        Movie movie = new Movie(20L, "My Life Without Me", "2003-03-07", "A fatally ill mother with only two months to live creates a list of things she wants to do before she dies without telling her family of her illness.", 5.879);
        movieRepository.save(movie);

        var movieById = movieRepository.findById(movie.getId());
        assertThat(movieById).isNotNull();

    }


}