package com.finalsdproject.animal.model;

import com.finalsdproject.owner.model.Owner;
import lombok.*;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private ESpecies animal;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String age;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false, length = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

}
