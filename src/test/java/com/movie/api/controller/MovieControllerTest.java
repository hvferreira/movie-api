package com.movie.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.model.Movie;
import com.movie.api.service.MovieService;
import com.movie.api.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class MovieControllerTest {
    @Mock
    private MovieServiceImpl mockMovieServiceImpl;

    @InjectMocks
    private MovieController movieController;

    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setup(){
        mockMvcController = MockMvcBuilders.standaloneSetup(movieController).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testGetAllMoviesReturnMovies() throws Exception{
        List<Movie> movies=new ArrayList<>();
        movies.add(new Movie(1L, "The Shawshank Redemption", "Frank Darabont", LocalDate.of(1994, Month.JANUARY, 1)));
        movies.add(new Movie(2L, "The Dark Knight", "Christopher Nolan", LocalDate.of(2008, Month.JULY, 18)));

        when(mockMovieServiceImpl.getAllMovies()).thenReturn(movies);

        this.mockMvcController.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("The Shawshank Redemption"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("The Dark Knight"));
    }

    @Test
    public void testPostMappingAddAMovie() throws Exception{
        // Step 1: Create a Movie object
        Movie movie = new Movie(4L, "Inception", "Christopher Nolan", LocalDate.of(2010, Month.JULY, 16));

        // Step 2: Mock the MovieManagerService and set expectations
        when(mockMovieServiceImpl.addMovie(movie)).thenReturn(movie);

        //Perform an HTTP POST request to the /movies endpoint with the Movie object as the request body
        this.mockMvcController.perform(
                        MockMvcRequestBuilders.post("/api/v1/movie/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(movie)))//Verify the HTTP response body contains the Movie object in JSON format
                .andExpect(MockMvcResultMatchers.status().isCreated());

        verify(mockMovieServiceImpl, times(1)).addMovie(movie);

    }

}

