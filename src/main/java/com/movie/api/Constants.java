package com.movie.api;

public class Constants {

    // KNOWN FOR
    public static final String KNOWN_FOR_ACTING = "Acting";
    public static final String KNOWN_FOR_DIRECTING = "Directing";
    public static final String KNOWN_FOR_WRITING = "Writing";

    // JOBS
    public static final String JOB_DIRECTING = "Director";
    public static final String JOB_WRITING = "Writer";
    public static final String JOB_PRODUCING = "Producer";

    // QUERY TYPES - to get from RESPONSES
    public static final String QUERY_CAST= "cast"; // used to get the cast of a movie
    public static final String QUERY_RESULTS= "results"; // used to get popular movies,
    public static final String QUERY_CREW= "crew"; // used to get director
    public static final String QUERY_GENRE= "genres"; // used to get categories/genre of a movie

    /*
    ENDPOINTS FOR EXTERNAL API
     */
    public static final String ENDPOINT_PERSON = "person"; // used to get actors, directors, writers, etc. (cast and crew)
    public static final String ENDPOINT_MOVIE = "movie";
    public static final String ENDPOINT_POPULAR = "popular"; // used for both person and movie

    public static final String ENDPOINT_CREDITS = "credits"; // for MOVIE
    public static final String ENDPOINT_CREDITS_PERSON = "movie_credits"; // for PERSON
    public static final String ENDPOINT_GENRE = "genre";
    public static final String ENDPOINT_TOP_RATED = "top_rated";
    public static final String ENDPOINT_RECOMMENDATIONS = "recommendations";
    public static final String ENDPOINT_SIMILAR = "similar";
    public static final String ENDPOINT_LATEST = "latest";

}
