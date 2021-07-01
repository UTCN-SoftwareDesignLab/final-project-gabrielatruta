package com.finalsdproject.sms;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class SMSService {

    public void send(SMS sms) {
        //wrote SECRET in order not to compromise the keys, will upload again after presentation
        String ACCOUNT_SID = "SECRET";
        String AUTH_TOKEN = "SECRET";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        String FROM_NUMBER = "+16014292297";
        Message.creator(new PhoneNumber(sms.getReceiver()), new PhoneNumber(FROM_NUMBER), sms.getMessage()).create();
    }

}
