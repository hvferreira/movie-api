package com.movie.api.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(){
        super ("Person not found");
    }
}
