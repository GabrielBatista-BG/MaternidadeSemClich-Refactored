package com.maternidade.maternidadeBackendApp.domain.repository;

import com.maternidade.maternidadeBackendApp.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    Usuario save(Usuario usuario);

    void updateSenha(String email, String senhaHash);

    void updateCodigoVerificacao(String email, String codigo);

    void clearCodigoVerificacao(String email);
}
