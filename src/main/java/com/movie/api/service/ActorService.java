package com.movie.api.service;
import com.movie.api.model.Actor;

import java.util.List;

public interface ActorService {
    Actor getActor(Long actorId);

    List<Actor> getActorsByMovieId(Long movieId);
}
