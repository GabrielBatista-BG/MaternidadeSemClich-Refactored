package com.maternidade.maternidadeBackendApp.domain.exception;

public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException() {
        super("Email já cadastrado.");
    }
}
