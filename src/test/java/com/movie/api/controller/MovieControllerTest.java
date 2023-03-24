package com.movie.api.controller;

import static org.mockito.Mockito.when;
import com.movie.api.Constants;
import com.movie.api.model.Director;
import com.movie.api.model.Genres;
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
import java.util.LinkedHashMap;
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
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovies.csv", numLinesToSkip = 1)
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

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovies.csv", numLinesToSkip = 1)
    void testMovieSimilar(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                          String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                          String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0)));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1)));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2)));

        when(movieService.getMovieRecommendationsSimilar(20L, Constants.ENDPOINT_SIMILAR)).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/20/similar"))
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

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovies.csv", numLinesToSkip = 1)
    void testMoviesWithActors(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                          String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                          String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0)));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1)));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2)));

        when(movieService.getMoviesWithActors(31L, 12898L)).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/actor?actor1=31&actor2=12898"))
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

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovies.csv", numLinesToSkip = 1)
    void testPopularMovies(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                              String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                              String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0)));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1)));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2)));

        when(movieService.getMovies(Constants.ENDPOINT_POPULAR)).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/popularMovies"))
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

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovies.csv", numLinesToSkip = 1)
    void testTopRatedMovies(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                           String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                           String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0)));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1)));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2)));

        when(movieService.getMovies(Constants.ENDPOINT_TOP_RATED)).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/topRatedMovies"))
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

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovies.csv", numLinesToSkip = 1)
    void testMoviesWithinRating(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                            String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                            String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0)));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1)));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2)));

        when(movieService.getMoviesWithinRating(7.2,9.2)).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/rating?rateMin=7.2&rateMax=9.2"))
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

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovies.csv", numLinesToSkip = 1)
    void findMoviesWithSpecificParameters(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                                String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                                String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0)));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1)));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2)));
        LinkedHashMap<String, String> parameterMap = new LinkedHashMap<>();
        parameterMap.put("genre", "28");
        parameterMap.put("from_date", "2000-10-02");
        parameterMap.put("rating", "9.0");
        parameterMap.put("time_available", "90");
        when(movieService.getMoviesWithParameters(parameterMap)).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/findMovies?rating=9.0&genre=28&date_from=2000-10-02&time_available=90"))
                .andExpect(MockMvcResultMatchers.status().isOk());/*
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
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].vote_average").value(Double.parseDouble(vote_average_2)));*/
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testGenrelist.csv", numLinesToSkip = 1)
    void testGenrelist(String id_0, String name_0, String id_1, String name_1, String id_2, String name_2, String id_3, String name_3) throws Exception {

        List<Genres> genres = new ArrayList<>();
        genres.add(new Genres(Long.parseLong(id_0), name_0));
        genres.add(new Genres(Long.parseLong(id_1), name_1));
        genres.add(new Genres(Long.parseLong(id_2), name_2));
        genres.add(new Genres(Long.parseLong(id_3), name_3));

        when(movieService.getGenreList()).thenReturn(genres);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/genrelist"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(id_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(name_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(id_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value(name_2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].id").value(id_3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[3].name").value(name_3));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovie.csv", numLinesToSkip = 1)
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
    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testMovie.csv", numLinesToSkip = 1)
    void testLatestMovie(String id, String original_title, String release_date, String overview, String vote_average) throws Exception {

        Movie movie = new Movie(Long.parseLong(id), original_title, release_date, overview, Double.parseDouble(vote_average));

        when(movieService.getLatestMovie()).thenReturn(movie);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/latestMovie"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value(original_title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_date").value(release_date))
                .andExpect(MockMvcResultMatchers.jsonPath("$.overview").value(overview))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vote_average").value(Double.parseDouble(vote_average)));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/MovieController/testDirector.csv", numLinesToSkip = 1)
    void testDirectorByMovie(String id, String name, String known_for_department, String birthday, String biography, String job) throws Exception {

        Director director = new Director();
        director.setId(Long.valueOf(id));
        director.setName(name);
        director.setKnown_for_department(known_for_department);
        director.setBirthday(birthday);
        director.setBiography(biography);
        director.setJob(job);

        when(movieService.getDirectorByMovie(28L)).thenReturn(director);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/28/director"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.known_for_department").value(known_for_department))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value(birthday))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biography").value(biography))
                .andExpect(MockMvcResultMatchers.jsonPath("$.job").value(job));

    }


    @Test
    void actorsByMovieId() {
    }
    @Test
    void chooseRandomMovie() {
    }

}