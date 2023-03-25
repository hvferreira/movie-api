package com.movie.api.service;

import com.movie.api.model.Movie;
import com.movie.api.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@PropertySource("/application.properties")
class MovieServiceTest {


    @Mock
    private MovieRepository mockMovieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @Test
    void testGetMovieById() {
        Long movieId = 20L;
        var movie = new Movie(20L, "My Life Without Me", "2003-03-07", "A fatally ill mother with only two months to live creates a list of things she wants to do before she dies without telling her family of her illness.", 5.879);


        when(mockMovieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        Movie actualResult = movieServiceImpl.getMovieById(movieId);

        assertThat(actualResult).isEqualTo(movie);
    }


}