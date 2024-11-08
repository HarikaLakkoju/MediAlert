package my.project.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService {
    @Value("${twilio.accountSid}")
    String account_Sid;
    @Value("${twilio.authToken}")
    String authToken;
    @Value("${twilio.phoneNumber}")
    String FromSmsNumber;

    private static final Logger logger = LoggerFactory.getLogger(SmsService.class);

    @PostConstruct
    private void setUp() {
        Twilio.init(account_Sid, authToken);

    }

    public boolean sendSms(String to, String message) {
        try {
            Message.creator(new PhoneNumber(to), new PhoneNumber(FromSmsNumber), message).create();
            logger.info("Sent SMS to: {}", to);
            return true;
        } catch (Exception e) {
            logger.error("Error sending SMS to {}: {}", to, e.getMessage());
            return false;
        }
    }
}
