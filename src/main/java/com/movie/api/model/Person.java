package com.movie.api.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Person {
    @Id
    private Long id;
    //@Column
    private String name;

    private String known_for_department;

    private String birthday;

    private String biography;




}
