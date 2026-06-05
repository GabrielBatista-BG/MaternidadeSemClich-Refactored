package com.maternidade.maternidadeBackendApp.application.usecase;

import com.maternidade.maternidadeBackendApp.application.dto.request.ConsultaRequest;
import com.maternidade.maternidadeBackendApp.domain.entity.Consulta;
import com.maternidade.maternidadeBackendApp.domain.repository.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriarConsultaUseCase {

    private final ConsultaRepository consultaRepository;

    public void execute(ConsultaRequest request) {
        Consulta consulta = Consulta.builder()
                .motivoConsulta(request.getMotivo())
                .dataConsulta(request.getData())
                .userId(request.getUserId())
                .build();

        consultaRepository.save(consulta);
    }
}
