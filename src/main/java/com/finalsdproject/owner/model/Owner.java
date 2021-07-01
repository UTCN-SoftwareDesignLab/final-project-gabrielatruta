package com.finalsdproject.owner.model;

import com.finalsdproject.animal.model.Animal;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String CNP;

    @Column(nullable = false)
    private String numericalCode;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
    private Set<Animal> animals;

}
