package br.fiap.com.global_solution_java.dto.request;


import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ProjetoRequest(

        @NotNull(message = "O nome do projeto deve ser informado")
        String nomeProjeto,

        @NotNull(message = "A descricao do projeto deve ser informada")
        String descricao,

        @NotNull(message = "A data de inicio do projeto deve ser informada")
        Date dataInicio,

        @NotNull(message = "A data de fim do projeto deve ser informada")
        Date dataFim,

        @NotNull
        AbstractRequest usuario
) {
}
