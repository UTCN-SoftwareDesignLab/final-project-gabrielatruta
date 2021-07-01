package com.finalsdproject.donation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class DonationDTO {
    private Long id;
    private String name;
    private Long donation;
}
