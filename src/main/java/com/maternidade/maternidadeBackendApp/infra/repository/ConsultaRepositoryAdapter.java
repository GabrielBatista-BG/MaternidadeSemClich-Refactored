package com.maternidade.maternidadeBackendApp.infra.repository;

import com.maternidade.maternidadeBackendApp.domain.entity.Consulta;
import com.maternidade.maternidadeBackendApp.domain.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsultaRepositoryAdapter implements ConsultaRepository {

    private final ConsultaJpaRepository jpa;

    @Override
    public Consulta save(Consulta consulta) {
        return jpa.save(consulta);
    }
}
