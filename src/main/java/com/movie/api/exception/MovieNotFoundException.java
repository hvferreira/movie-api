package com.movie.api.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(){
        super ("Movie not found");
    }
}
