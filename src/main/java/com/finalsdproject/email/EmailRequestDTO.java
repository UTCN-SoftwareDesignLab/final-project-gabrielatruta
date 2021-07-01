package com.finalsdproject.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EmailRequestDTO {
    private String subject;
    private String message;
}
