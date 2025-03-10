package com.app.config;


import com.twilio.Twilio;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Getter
    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Bean
    public void init() {
        Twilio.init(accountSid, authToken);
    }

}
