package com.movie.api.model;

import jakarta.persistence.Entity;
import lombok.*;


public class Actor extends Person {

    public Actor(Long id, String name, String known_for_department, String birthday, String biography, String job) {
        super(id, name, known_for_department, birthday, biography, job);
    }
}
