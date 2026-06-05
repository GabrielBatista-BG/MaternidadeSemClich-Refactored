package com.maternidade.maternidadeBackendApp.infra.repository;

import com.maternidade.maternidadeBackendApp.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.senha = :senha WHERE u.email = :email")
    void updateSenha(@Param("email") String email, @Param("senha") String senha);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.codigoVerificacao = :codigo WHERE u.email = :email")
    void updateCodigoVerificacao(@Param("email") String email, @Param("codigo") String codigo);

    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.codigoVerificacao = NULL WHERE u.email = :email")
    void clearCodigoVerificacao(@Param("email") String email);
}
