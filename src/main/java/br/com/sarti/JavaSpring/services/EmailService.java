package br.com.sarti.JavaSpring.services;

import br.com.sarti.JavaSpring.config.EmailConfig;
import br.com.sarti.JavaSpring.mail.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class EmailService {

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailConfig emailConfigs;

    public void sendSimpleEmail( String to, String subject, String body) {
        emailSender
                .to(to)
                .withSubject(subject)
                .withMessage(body)
                .send(emailConfigs);

    }

}
