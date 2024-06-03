package br.fiap.com.global_solution_java.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AlertaRequest(


        @NotNull(message = "O tipo de alerta deve ser informado")
        String tipoAlerta,

        @NotNull(message = "A descricao do alerta deve ser informada")
        String descricao,

        @NotNull(message = "A data de criacao do alerta deve ser informada")
        Date dataGeracao,

        @NotNull
        AbstractRequest habitat,

        @NotNull
        AbstractRequest usuario
) {
}
