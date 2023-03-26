package com.movie.api.controller;

import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import com.movie.api.service.ActorService;
import com.movie.api.service.SearchService;
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

import static org.mockito.Mockito.when;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private SearchService searchService;

    @MockBean
    ActorService actorService;

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/SearchController/testMovies.csv", numLinesToSkip = 1)
    void testMovieSearch(String id_0, String original_title_0, String release_date_0, String overview_0, String vote_average_0,
                         String id_1, String original_title_1, String release_date_1, String overview_1, String vote_average_1,
                         String id_2, String original_title_2, String release_date_2, String overview_2, String vote_average_2) throws Exception {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie(Long.parseLong(id_0), original_title_0, release_date_0, overview_0, Double.parseDouble(vote_average_0), false));
        movies.add(new Movie(Long.parseLong(id_1), original_title_1, release_date_1, overview_1, Double.parseDouble(vote_average_1), false));
        movies.add(new Movie(Long.parseLong(id_2), original_title_2, release_date_2, overview_2, Double.parseDouble(vote_average_2), false));

        when(searchService.searchMovies("lord of the rings")).thenReturn(movies);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/search/movie/lord of the rings"))
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
    @CsvFileSource(resources = "/testFiles/Controller/SearchController/testActors.csv", numLinesToSkip = 1)
    void testActorSearch(String id_0, String name_0, String known_for_department_0, String birthday_0, String biography_0,
                         String id_1, String name_1, String known_for_department_1, String birthday_1, String biography_1) throws Exception {

        List<Actor> actors = new ArrayList<>();
        Actor actor1 = new Actor();
        actor1.setId(Long.parseLong(id_0));
        actor1.setName(name_0);
        actor1.setKnown_for_department(known_for_department_0);
        actor1.setBirthday(birthday_0);
        actor1.setBiography(biography_0);

        Actor actor2 = new Actor();
        actor2.setId(Long.parseLong(id_1));
        actor2.setName(name_1);
        actor2.setKnown_for_department(known_for_department_1);
        actor2.setBirthday(birthday_1);
        actor2.setBiography(biography_1);

        actors.add(actor1);
        actors.add(actor2);
        when(searchService.searchActor("Tom")).thenReturn(actors);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/search/actor/Tom"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].known_for_department").value(known_for_department_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthday").value(birthday_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].biography").value(biography_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(id_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(name_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].known_for_department").value(known_for_department_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].birthday").value(birthday_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].biography").value(biography_1));

    }

}
