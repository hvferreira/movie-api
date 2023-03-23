package com.movie.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.movie.api.Constants;
import com.movie.api.model.Movie;
import com.movie.api.service.ActorService;
import com.movie.api.service.MovieService;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

//@ExtendWith(StringEx.class)
@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;

    @MockBean
    ActorService actorService;


    @Test
    void defaultMapping() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovieRecommendations.csv", numLinesToSkip = 1)
    void testMovieRecommendations(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                                  String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                                  String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0)));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1)));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2)));

        when(movieService.getMovieRecommendationsSimilar(20L, Constants.ENDPOINT_RECOMMENDATIONS)).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/20/recommendations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].original_title").value(original_title_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].release_date").value(release_date_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].overview").value(overview_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vote_average").value(Double.parseDouble(vote_average_0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(id_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].original_title").value(original_title_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].release_date").value(release_date_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].overview").value(overview_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].vote_average").value(Double.parseDouble(vote_average_1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(id_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].original_title").value(original_title_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].release_date").value(release_date_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].overview").value(overview_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].vote_average").value(Double.parseDouble(vote_average_2)));
    }

    @Test
    void movieSimilar() {
    }

    @Test
    void moviesWithActors() {
    }

    @Test
    void directorByMovie() {
    }

    @Test
    void genrelist() {
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovieByID.csv", numLinesToSkip = 1)
    void testMovieByID(String id, String original_title, String release_date, String overview, String vote_average) throws Exception {

        Movie movie = new Movie(Long.parseLong(id), original_title, release_date, overview, Double.parseDouble(vote_average));

        when(movieService.getMovieById(movie.getId())).thenReturn(movie);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/" + movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value(original_title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_date").value(release_date))
                .andExpect(MockMvcResultMatchers.jsonPath("$.overview").value(overview))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vote_average").value(Double.parseDouble(vote_average)));

    }

    @Test
    void popularMovies() {
    }

    @Test
    void topRatedMovies() {
    }

    @Test
    void latestMovie() {
    }

    @Test
    void actorsByMovieId() {
    }

    @Test
    void moviesWithinRating() {
    }

    @Test
    void chooseRandomMovie() {
    }

    @Test
    void findMoviesWithSpecificParameters() {
    }
}