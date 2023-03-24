package com.movie.api.model;

import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Director extends Person{

    private String job;


    public Director(Long id, String name, String known_for_department, String birthday, String biography, String job) {
        super(id, name, known_for_department, birthday, biography);
        this.job = job;
    }
}
