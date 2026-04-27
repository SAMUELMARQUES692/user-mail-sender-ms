package dev.java10x.user.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EmailDTO {

    private UUID userId;
    private String emailTo;
    private String emailSubject;
    private String body;

}
