package com.app.service.evaluation;

import com.app.config.TwilioWPConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService {
    private final TwilioWPConfig twilioWPConfig;

    @Autowired
    public WhatsappService(TwilioWPConfig twilioWPConfig) {
        this.twilioWPConfig = twilioWPConfig;
    }

    public void sendWhatsappMessage(String to, String body) {
        Message.creator(
                new PhoneNumber("whatsapp:" + to),
                new PhoneNumber("whatsapp:" + twilioWPConfig.getWhatsappNumber()),
                body
        ).create();
    }
}