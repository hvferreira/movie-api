package com.movie.api.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Person {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(updatable = false, nullable = false)
    private Long id;
    //@Column
    private String name;

    private String known_for_department;

    private String birthday;

    private String biography;

}
