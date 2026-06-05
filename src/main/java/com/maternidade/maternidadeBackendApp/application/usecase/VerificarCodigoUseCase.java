package com.maternidade.maternidadeBackendApp.application.usecase;

import com.maternidade.maternidadeBackendApp.application.dto.request.VerificarCodigoRequest;
import com.maternidade.maternidadeBackendApp.domain.entity.Usuario;
import com.maternidade.maternidadeBackendApp.domain.exception.CodigoVerificacaoInvalidoException;
import com.maternidade.maternidadeBackendApp.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificarCodigoUseCase {

    private final UsuarioRepository usuarioRepository;

    public void execute(VerificarCodigoRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getTo())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        String codigoArmazenado = usuario.getCodigoVerificacao();

        if (codigoArmazenado == null || !codigoArmazenado.equals(request.getCode())) {
            throw new CodigoVerificacaoInvalidoException();
        }

        usuarioRepository.clearCodigoVerificacao(request.getTo());
    }
}
