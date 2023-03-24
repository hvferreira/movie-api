package com.movie.api.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.api.model.AuthorDetails;
import com.movie.api.model.Movie;
import com.movie.api.model.MovieReviews;
import com.movie.api.service.MovieReviewService;
import com.movie.api.service.MovieReviewServiceImpl;
import com.movie.api.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;



@WebMvcTest(MovieReviewController.class)
public class MovieReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieReviewService movieReviewService;
    @MockBean
    private MovieService movieService;
    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/testMovieReviewsByMovieId.csv", numLinesToSkip = 1)
    public void testGetReviewsByMovieId(long movieId,String id, String author, String authorDetailsName, String authorDetailsUsername,
                                        String authorDetailsAvatarPath, String authorDetailsRating, String content,
                                        String createdAt, String updatedAt, String url)throws Exception{

         content = content.replaceAll("\\*\\*", "");
        // Convert CSV data to expected entity
        AuthorDetails authorDetails = new AuthorDetails(authorDetailsName, authorDetailsUsername,
                authorDetailsAvatarPath, Double.parseDouble(authorDetailsRating));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime createdAtTime = LocalDateTime.parse(createdAt, formatter);
        LocalDateTime updatedAtTime = LocalDateTime.parse(updatedAt, formatter);
        MovieReviews expectedReview = new MovieReviews(id, author, authorDetails, content.replaceAll("\\r|\\n", ""),
                Double.parseDouble(authorDetailsRating), createdAtTime, updatedAtTime, url);



        Movie movie = new Movie(28L, "Test Movie", "2022-01-01", "This is a test movie.", 5.0);

        when(movieService.getMovieById(movie.getId())).thenReturn(movie);

        // Mock service layer method to return expected result
        List<MovieReviews> expectedReviews = new ArrayList<>();
        expectedReviews.add(expectedReview);
        Mockito.when(movieReviewService.getReviewsByMovieId(movieId)).thenReturn(expectedReviews);

        // Invoke endpoint and perform assertions
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movie/" + movieId + "/reviews"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value(author))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_details.name").value(authorDetailsName))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_details.username").value(authorDetailsUsername))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_details.avatar_path").value(authorDetailsAvatarPath))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author_details.rating").value(Double.parseDouble(authorDetailsRating)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(content.replaceAll("\\r|\\n", "")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].created_at").value(createdAtTime.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].updated_at").value(updatedAtTime.toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].url").value(url));
    }
}

