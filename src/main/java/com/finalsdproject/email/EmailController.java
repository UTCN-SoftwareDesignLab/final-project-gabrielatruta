package com.finalsdproject.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.finalsdproject.URLMapping.EMAIL;

@RestController
@RequestMapping(EMAIL)
@RequiredArgsConstructor
public class EmailController {
    private final EmailServiceFactory emailServiceFactory;

    @PostMapping
    public void sendAdoptionRequest(@RequestBody EmailRequestDTO emailRequestDTO) {
        emailServiceFactory.sendMail(emailRequestDTO);
    }
}
