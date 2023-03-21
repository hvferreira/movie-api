package com.movie.api.service;

import com.movie.api.model.Movie;
import com.movie.api.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
public class MovieServiceTest {
    @Mock
    private MovieRepository mockMovieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;
  @Test
    public void testGetAllBooksReturnsListOfBooks() {

        List<Movie> movies = new ArrayList<>();
      movies.add(new Movie(1L, "The Shawshank Redemption", "Frank Darabont", LocalDate.of(1994, Month.JANUARY, 1)));
      movies.add(new Movie(2L, "The Dark Knight", "Christopher Nolan", LocalDate.of(2008, Month.JULY, 18)));


      when(mockMovieRepository.findAll()).thenReturn(movies);

        List<Movie> actualResult = movieServiceImpl.getAllMovies();

        assertThat(actualResult).hasSize(2);
        assertThat(actualResult).isEqualTo(movies);
    }
}
