package br.fiap.com.global_solution_java.dto.request;


import jakarta.validation.constraints.NotNull;

public record HabitatRequest(

        @NotNull(message = "O nome do habitat deve ser informado")
        String nomeHabitat,

        @NotNull(message = "A descricao do habitat deve ser informado")
        String descricao,

        @NotNull(message = "A localizacao do habitat deve ser informada")
        String localizacao,

        @NotNull(message = "O status do habitat deve ser informado")
        String status,

        @NotNull
        AbstractRequest projeto
) {
}
