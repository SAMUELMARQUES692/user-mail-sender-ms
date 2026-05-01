package dev.java10x.user.exception;

public class EmailDuplicadoException extends RuntimeException {
    
    public EmailDuplicadoException(String email) {
        super("O email '" + email + "' já está registrado no sistema.");
    }
}

