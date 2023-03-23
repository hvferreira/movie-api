package com.movie.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

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

//@ExtendWith(StringEx.class)
@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MovieService movieService;

    @MockBean
    ActorService actorService;

    @ParameterizedTest
    @CsvFileSource(resources = "/testFiles/testMovieByID.csv", numLinesToSkip = 1)
    void testMovieByID(String id, String original_title, String release_date, String overview, String vote_average) throws Exception {
        //Movie movie = new Movie(20L, "My Life Without Me", "2003-03-07",
        //      "A fatally ill mother with only two months to live creates a list of things", 2.0);

        Movie movie = new Movie(Long.parseLong(id), original_title, release_date, overview, Double.parseDouble(vote_average));

        when(movieService.getMovieById(movie.getId())).thenReturn(movie);

        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/" + movie.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Long.parseLong(id)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.original_title").value(original_title))
                .andExpect(MockMvcResultMatchers.jsonPath("$.release_date").value(release_date))
                .andExpect(MockMvcResultMatchers.jsonPath("$.overview").value(overview))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vote_average").value(Double.parseDouble(vote_average)));
        ;


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
    void testMovieRecommendations() throws Exception {
//        List<Movie> movies = new ArrayList<>();
//        movies.add(new Movie(20L, "My Life Without Me", "2003-03-07",
//                "A fatally ill mother with only two months to live creates a list of things"));
//        movies.add(new Movie(20L, "My Life Without Me", "2003-03-07",
//                "A fatally ill mother with only two months to live creates a list of things"));
//        movies.add(new Movie(20L, "My Life Without Me", "2003-03-07",
//                "A fatally ill mother with only two months to live creates a list of things"));
//
//        when(movieService.getMovieRecommendationsSimilar(20L, Constants.ENDPOINT_RECOMMENDATIONS)).thenReturn(movies);
//
//        this.mockMvc.perform(
//                        MockMvcRequestBuilders.get("/api/v1/movie/20/recommendations"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(20L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].original_title").value("My Life Without Me"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].release_date").value("2003-03-07"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].overview").value("A fatally ill mother with only two months to live creates a list of things"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(20L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].original_title").value("My Life Without Me"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].release_date").value("2003-03-07"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].overview").value("A fatally ill mother with only two months to live creates a list of things"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(20L))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].original_title").value("My Life Without Me"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].release_date").value("2003-03-07"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$[2].overview").value("A fatally ill mother with only two months to live creates a list of things"));

    }

    @Test
    void testMovieSimilar() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/20/similar"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testGenrelist() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/genrelist"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void testPopularMovies() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/popularMovies"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testTopRatedMovies() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/topRatedMovies"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testLatestMovie() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/latestMovie"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testActorById() throws Exception {
        this.mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/movie/20/actors"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testHealth() throws Exception {
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