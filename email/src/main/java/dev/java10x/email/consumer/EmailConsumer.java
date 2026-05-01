package dev.java10x.email.consumer;

import dev.java10x.email.dto.EmailDTO;
import dev.java10x.email.model.EmailModel;
import dev.java10x.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService emailService;

    @RabbitListener(queues = "email-queue")
    public void listenEmailQueue(@Payload EmailDTO emailDTO) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        emailService.sendEmail(emailModel);
    }

}
