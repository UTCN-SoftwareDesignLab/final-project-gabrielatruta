package com.finalsdproject.sms;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finalsdproject.URLMapping.SMS;

@RestController
@RequestMapping(SMS)
@RequiredArgsConstructor
public class SMSController {

    private final SMSService smsService;

    @PostMapping
    public void sendSMS(@RequestBody SMS sms){
        smsService.send(sms);
    }

}
