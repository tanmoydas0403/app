package com.app.config;

import com.twilio.Twilio;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioWPConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Getter
    @Value("${twilio.whatsapp.number}")
    private String whatsappNumber;

    @Bean
    public void initializeTwilio() {
        Twilio.init(accountSid, authToken);
    }
}