package dev.java10x.email.service;

import dev.java10x.email.enums.EmailStatus;
import dev.java10x.email.model.EmailModel;
import dev.java10x.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
   public void sendEmail(EmailModel emailModel) {
       try {
           SimpleMailMessage message = new SimpleMailMessage();
           message.setFrom(emailFrom);
           message.setTo(emailModel.getEmailTo());
           message.setSubject(emailModel.getEmailSubject());
           message.setText(emailModel.getBody());
           javaMailSender.send(message);
           emailModel.setStatusEmail(EmailStatus.SENT);
           emailModel.setSendAt(LocalDateTime.now());
       } catch (Exception exception) {
           emailModel.setStatusEmail(EmailStatus.FAILED);
           System.out.println("Erro ao enviar email: " + exception.getMessage());
       }
       emailRepository.save(emailModel);
   }
}

