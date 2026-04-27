package dev.java10x.email.dto;

import java.util.UUID;

public record EmailDTO(
        UUID userId,
        String emailTo,
        String emailSubject,
        String body
) {
}
