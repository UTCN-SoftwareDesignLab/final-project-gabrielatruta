package com.finalsdproject.owner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDTO {

    private Long id;
    private String name;
    private String address;
    private String CNP;
    private String numericalCode;
    private String phone;
    private Long animalID;

}
