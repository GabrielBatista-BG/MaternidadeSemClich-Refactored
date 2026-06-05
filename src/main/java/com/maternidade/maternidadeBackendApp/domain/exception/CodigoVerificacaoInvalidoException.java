package com.maternidade.maternidadeBackendApp.domain.exception;

public class CodigoVerificacaoInvalidoException extends RuntimeException {
    public CodigoVerificacaoInvalidoException() {
        super("Código de verificação inválido.");
    }
}
