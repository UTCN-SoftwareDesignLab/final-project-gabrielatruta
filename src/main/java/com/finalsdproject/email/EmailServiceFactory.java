package com.finalsdproject.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceFactory implements EmailService{

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMail(EmailRequestDTO emailRequestDTO) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo("gabriela.truta99@yahoo.com");
        simpleMailMessage.setSubject(emailRequestDTO.getSubject());
        simpleMailMessage.setText(emailRequestDTO.getMessage());

        emailSender.send(simpleMailMessage);
    }
}
