package com.maternidade.maternidadeBackendApp.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consultas_psicologia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "motivo_consulta", nullable = false)
    private String motivoConsulta;

    @Column(name = "data_consulta", nullable = false)
    private String dataConsulta;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
