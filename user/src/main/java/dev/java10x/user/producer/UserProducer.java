package dev.java10x.user.producer;

import dev.java10x.user.dto.EmailDTO;
import dev.java10x.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProducer {

    private final RabbitTemplate rabbitTemplate;

    private final String routingKey = "email-queue";

    public void publishEvent(UserModel userModel) {
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(userModel.getUserId());
        emailDTO.setEmailTo(userModel.getEmail());
        emailDTO.setEmailSubject("Welcome developers");
        emailDTO.setBody("Hello " + userModel.getName() + ",\n\nWelcome, developers! We are excited for you to test our functionalities.");

        rabbitTemplate.convertAndSend(
                "",
                routingKey,
                emailDTO);
    }
}
