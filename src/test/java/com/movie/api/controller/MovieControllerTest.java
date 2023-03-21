package com.movie.api.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.movie.api.service.MovieService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(StringEx.class)
@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;

    @Test
    void movieByID() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/movie/20");
        System.out.println("HHEEELLLOOOO " + request);
        MvcResult result = mockMvc.perform(request).andReturn();
        //assertEquals("OLA", result.getResponse().getContentAsString());

        //*********************************************************************************
        //*********************************************************************************


    }


    @Test
    void movieRecommendations() {
    }

    @Test
    void movieSimilar() {
    }

    @Test
    void genrelist() {
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
    void actorById() {
    }

    @Test
    void health() throws Exception {
        this.mockMvc.perform(get("/health")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(Health.up().toString())));
    }
}