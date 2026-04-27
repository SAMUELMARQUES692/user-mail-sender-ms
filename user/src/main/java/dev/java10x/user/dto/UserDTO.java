package dev.java10x.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UserDTO(

        UUID userId,

        @NotBlank
        String name,

        @Email
        @NotBlank
        String email) {
}
