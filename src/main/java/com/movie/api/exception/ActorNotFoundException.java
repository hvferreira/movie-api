package com.movie.api.exception;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException(){
        super ("Actor not found");
    }
}
