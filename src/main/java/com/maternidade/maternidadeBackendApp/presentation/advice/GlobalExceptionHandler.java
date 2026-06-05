package com.maternidade.maternidadeBackendApp.presentation.advice;

import com.maternidade.maternidadeBackendApp.application.dto.response.MensagemResponse;
import com.maternidade.maternidadeBackendApp.domain.exception.CodigoVerificacaoInvalidoException;
import com.maternidade.maternidadeBackendApp.domain.exception.CredenciaisInvalidasException;
import com.maternidade.maternidadeBackendApp.domain.exception.EmailJaCadastradoException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CredenciaisInvalidasException.class)
    public ResponseEntity<MensagemResponse> handleCredenciaisInvalidas(CredenciaisInvalidasException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MensagemResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<MensagemResponse> handleEmailJaCadastrado(EmailJaCadastradoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MensagemResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(CodigoVerificacaoInvalidoException.class)
    public ResponseEntity<MensagemResponse> handleCodigoInvalido(CodigoVerificacaoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MensagemResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<MensagemResponse> handleJwtException(JwtException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(MensagemResponse.of("Token inválido ou expirado."));
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<MensagemResponse> handleSecurityException(SecurityException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(MensagemResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MensagemResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        String mensagem = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MensagemResponse.of(mensagem));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MensagemResponse> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(MensagemResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensagemResponse> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(MensagemResponse.of("Erro interno no servidor."));
    }
}
