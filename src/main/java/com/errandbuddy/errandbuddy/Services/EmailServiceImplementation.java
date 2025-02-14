package com.errandbuddy.errandbuddy.Services;

import com.errandbuddy.errandbuddy.Dto.request.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImplementation implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("aleroutieyione@gmail.com")
    private String senderEmail;

    @Override
    public void sendEmailAlert(EmailDetails emailDetails) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderEmail);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMessageBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);
            System.out.println("Mail Sent Successfully");
//        } catch (MailException e) {
//            throw new RuntimeException(e);
        } catch (MailException e) {
                System.err.println("Error Sending Email: " + e.getMessage());
                e.printStackTrace();
        }
    }

    @Override
    public void sendEmailAlert(String email, String newErrand, String messageBody) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(email);
            message.setText(messageBody);
            message.setSubject(newErrand);

            javaMailSender.send(message);
            System.out.println("Mail Sent Successfully");

        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }
}
