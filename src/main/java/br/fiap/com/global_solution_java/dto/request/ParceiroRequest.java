package br.fiap.com.global_solution_java.dto.request;


import jakarta.validation.constraints.NotNull;

public record ParceiroRequest(

        @NotNull(message = "O nome do parceiro deve ser informado")
        String nomeParceiro,

        @NotNull(message = "O tipo do parceiro deve ser informado")
        String tipoParceiro,

        @NotNull(message = "O contato do parceiro deve ser informado")
        String contato,

        @NotNull(message = "O email do parceiro deve ser informado")
        String email,

        @NotNull(message = "O telefone do parceiro deve ser informado")
        String telefone
) {
}
