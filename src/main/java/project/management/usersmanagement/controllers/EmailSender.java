package project.management.usersmanagement.controllers;

import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController

public class EmailSender {
    public void sendCouponCode(String recipient, String couponCode) throws MessagingException {
        // Email server settings
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "nourhene.chalghoumi@esprit.tn";
        String password = "224JFT0485";
        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Authenticate with the email server
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create the email message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject("Your coupon code");

        // Load the email template and replace placeholders with the coupon code
        String content = "Dear user,\n\nThank you for your order. Your coupon code is: " + couponCode;
        message.setText(content);

        // Send the email
        Transport.send(message);
    }
}
