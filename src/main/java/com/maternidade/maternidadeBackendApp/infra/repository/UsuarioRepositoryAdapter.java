package com.maternidade.maternidadeBackendApp.infra.repository;

import com.maternidade.maternidadeBackendApp.domain.entity.Usuario;
import com.maternidade.maternidadeBackendApp.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepository {

    private final UsuarioJpaRepository jpa;

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpa.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpa.existsByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return jpa.save(usuario);
    }

    @Override
    public void updateSenha(String email, String senhaHash) {
        jpa.updateSenha(email, senhaHash);
    }

    @Override
    public void updateCodigoVerificacao(String email, String codigo) {
        jpa.updateCodigoVerificacao(email, codigo);
    }

    @Override
    public void clearCodigoVerificacao(String email) {
        jpa.clearCodigoVerificacao(email);
    }
}
