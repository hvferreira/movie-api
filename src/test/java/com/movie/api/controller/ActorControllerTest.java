package com.movie.api.controller;

import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import com.movie.api.model.Person;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(ActorController.class)
class ActorControllerTest {


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
    @CsvFileSource(resources = "/testFiles/Controller/ActorController/testActorById.csv", numLinesToSkip = 1)
    void testActorById(String id, String name, String known_for_department, String birthday, String biography) throws Exception {

        Actor actor = new Actor(Long.parseLong(id), name, known_for_department, birthday, biography, "Test");

        when(actorService.getActor(actor.getId())).thenReturn(actor);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/actor/" + actor.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.known_for_department").value(known_for_department))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value(birthday))
                .andExpect(MockMvcResultMatchers.jsonPath("$.biography").value(biography));

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/ActorController/testPopularActors.csv", numLinesToSkip = 1)
    void testPopularActors() {
    }
}