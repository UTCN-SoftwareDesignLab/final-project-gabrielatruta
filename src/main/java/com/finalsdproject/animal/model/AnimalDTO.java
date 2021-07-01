package com.finalsdproject.animal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AnimalDTO {

    private Long id;
    private String animal;
    private String name;
    private String age;
    private String gender;
    private String size;
    private String description;
}
