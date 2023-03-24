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


}