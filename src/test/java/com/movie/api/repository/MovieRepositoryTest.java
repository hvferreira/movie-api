package com.movie.api.repository;

import com.movie.api.Model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testFindAllMoviesReturnsMovies(){
        Movie movie=new Movie(1L, "The Shawshank Redemption", "Frank Darabont", LocalDate.of(1994, Month.JANUARY, 1));
      movieRepository.save(movie);

        Iterable<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(1);

    }
}
