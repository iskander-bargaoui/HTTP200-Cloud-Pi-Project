package tn.esprit.pibakcend.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pibakcend.config.TwilioConfiguration;

@Service("twilio")

public class TwillioSmsSender implements SmsSender{
    private static final Logger LOGGER = LoggerFactory.getLogger(TwillioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwillioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber("+21623743004");
            PhoneNumber from = new PhoneNumber("+16205368382");
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number"
            );
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        String phoneRegex = "^\\+\\d{10,}$";
        return phoneNumber.matches(phoneRegex);
    }
}
