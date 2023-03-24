package com.movie.api.controller;

import com.movie.api.Constants;
import com.movie.api.model.Actor;
import com.movie.api.model.Movie;
import com.movie.api.model.Person;
import com.movie.api.service.ActorService;
import com.movie.api.service.MovieService;
import org.checkerframework.checker.units.qual.A;
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

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/Controller/ActorController/testActorById.csv", numLinesToSkip = 1)
    void testActorById(String id, String name, String known_for_department, String birthday, String biography) throws Exception {

        Actor actor = new Actor();
        actor.setId(Long.parseLong(id));
        actor.setName(name);
        actor.setKnown_for_department(known_for_department);
        actor.setBirthday(birthday);
        actor.setBiography(biography);

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
    void testPopularActors(String id_0, String name_0, String known_for_department_0, String birthday_0, String biography_0,
                           String id_1, String name_1, String known_for_department_1, String birthday_1, String biography_1) throws Exception {
        Actor actor_one = new Actor();
        actor_one.setId(Long.parseLong(id_0));
        actor_one.setName(name_0);
        actor_one.setKnown_for_department(known_for_department_0);
        actor_one.setBirthday(birthday_0);
        actor_one.setBiography(biography_0);

        Actor actor_two = new Actor();
        actor_two.setId(Long.parseLong(id_1));
        actor_two.setName(name_1);
        actor_two.setKnown_for_department(known_for_department_1);
        actor_two.setBirthday(birthday_1);
        actor_two.setBiography(biography_1);

        List<Actor> actors = new ArrayList<>();
        actors.add(actor_one);
        actors.add(actor_two);

        when(actorService.getPopularActors()).thenReturn(actors);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/actor/popular"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(name_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].known_for_department").value(known_for_department_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].birthday").value(birthday_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].biography").value(biography_0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(id_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(name_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].known_for_department").value(known_for_department_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].birthday").value(birthday_1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].biography").value(biography_1));
    }
}