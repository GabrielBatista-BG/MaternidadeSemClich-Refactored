package com.maternidade.maternidadeBackendApp.infra.repository;

import com.maternidade.maternidadeBackendApp.domain.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaJpaRepository extends JpaRepository<Consulta, Long> {
}
