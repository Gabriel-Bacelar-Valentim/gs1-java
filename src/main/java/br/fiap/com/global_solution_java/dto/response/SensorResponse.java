package br.fiap.com.global_solution_java.dto.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record SensorResponse(
        Long id,

        String tipoSensor,

        String descricao,

        String localizacao,

        Date dataInstalacao,

        ProjetoResponse projeto
) {
}
