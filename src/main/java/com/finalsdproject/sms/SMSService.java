package com.finalsdproject.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class SMSService {

    private final String ACCOUNT_SID = "AC4b629f3c44f94412fbf504a8ab4d40ce";

    private final String AUTH_TOKEN = "d41c866c56bf8b8a5179c923601725f9";

    private final String FROM_NUMBER = "+16014292297";

    public void send(SMS sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(sms.getReceiver()), new PhoneNumber(FROM_NUMBER), "GAMBARE GAMBARE!").create();

    }

}
