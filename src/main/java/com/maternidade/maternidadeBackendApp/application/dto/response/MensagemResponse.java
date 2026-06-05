package com.maternidade.maternidadeBackendApp.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensagemResponse {
    private String mensagem;

    public static MensagemResponse of(String mensagem) {
        return new MensagemResponse(mensagem);
    }
}
