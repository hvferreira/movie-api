package com.movie.api.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.movie.api.model.Movie;
import com.movie.api.service.ActorService;
import com.movie.api.service.MovieService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.print.Book;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @ResponseBody
    void movieByID() throws Exception {


        Movie movie = new Movie(20L, "My Life Without Me", "2003-03-07",
                "A fatally ill mother with only two months to live creates a list of things");

        when(movieService.getMovieById(movie.getId())).thenReturn(movie);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/" + movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(20L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value("My Life Without Me"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_date").value("2003-03-07"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.overview").value("A fatally ill mother with only two months to live creates a list of things"));


        //RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/movie/20");
        // MvcResult result = mockMvc.perform(request).andReturn();
        // assertEquals(20, result.getResponse().getContentAsString());





       /* mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value("My Life Without Me"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_date").value("My Life Without Me"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.overview").value("A fatally ill mother with only two months to live creates a list of things she wants to do before she dies without telling her family of her illness."));
*/
       /* mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/20")).andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value("My Life Without Me"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_date").value("2003-03-07"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.overview").value("A fatally ill mother with only two months to live creates a list of things she wants to do before she dies without telling her family of her illness."));
*/

      /*  mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/movie/20")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(20))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value("My Life Without Me"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_date").value("2003-03-07"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.overview").value("A fatally ill mother with only two months to live creates a list of things she wants to do before she dies without telling her family of her illness."));
*/
    }


    @Test
    void movieRecommendations() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/20/recommendations"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void movieSimilar() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/20/similar"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void genrelist() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/genrelist"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void popularMovies() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/popularMovies"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void topRatedMovies() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/topRatedMovies"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void latestMovie() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/latestMovie"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void actorById() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/20/actors"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @ResponseBody
    void health() throws Exception {
        // this.mockMvc.perform(get("/api/v1/movie/health")).andDo(print()).andExpect(status().isOk())
        //       .andExpect(content().string(containsString(String.valueOf(Health.up().build()))));

        //RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/movie/health");
        //MvcResult result = mockMvc.perform(request).andReturn();
        //assertEquals("{\"status\":\"UP\"}", result.getResponse().getStatus());
        //assertEquals(Health.up().build(), result.getResponse().getContentAsString());
        //assertEquals(200, result.getResponse().getStatus());

        //mockMvc.perform(get("/actuator/health/random"))
        //      .andExpect(status().isNotFound());

        //mockMvc.perform(get("/actuator/health/random"))
        //      .andExpect(jsonPath("$.status").exists())
        //    .andExpect(jsonPath("$.details.strategy").value("thread-local"))
        //  .andExpect(jsonPath("$.details.chance").exists());


        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/health"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("UP"));
    }
}