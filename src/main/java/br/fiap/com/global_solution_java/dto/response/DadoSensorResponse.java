package br.fiap.com.global_solution_java.dto.response;

import lombok.Builder;

@Builder
public record DadoSensorResponse(
    Long id,

    Double temperatura,

    Double salinidade,

    Double nivelPh,

    String localizacao,

    SensorResponse sensor
) {
}
