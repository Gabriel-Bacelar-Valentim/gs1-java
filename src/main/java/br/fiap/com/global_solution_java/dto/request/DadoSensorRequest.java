package br.fiap.com.global_solution_java.dto.request;


import jakarta.validation.constraints.NotNull;

public record DadoSensorRequest(

        @NotNull(message = "A temperatura deve ser informada")
        Double temperatura,

        @NotNull(message = "O nivel de salinidade deve ser informado")
        Double salinidade,

        @NotNull(message = "O nivel do p deve ser informado")
        Double nivelPh,

        @NotNull(message = "A localizacao deve ser informada")
        String localizacao,

        @NotNull
        AbstractRequest sensor
) {
}
