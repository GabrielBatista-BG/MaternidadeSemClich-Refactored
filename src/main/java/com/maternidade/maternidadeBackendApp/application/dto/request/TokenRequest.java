package com.maternidade.maternidadeBackendApp.application.dto.request;

import lombok.Data;

@Data
public class TokenRequest {
    private String accessToken;
    private String refreshToken;
}
