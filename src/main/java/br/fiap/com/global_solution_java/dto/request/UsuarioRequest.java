package br.fiap.com.global_solution_java.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UsuarioRequest(

        @NotNull(message = "O nome do usuario deve ser informado")
        String nomeUsuario,

        @NotNull(message = "O email do usuario deve ser informado")
        String email,

        @NotNull(message = "A senha do usuario deve ser informada")
        String senha,

        @NotNull(message = "O cargo do usuario deve ser informado")
        String cargo,

        @NotNull(message = "A data de criacao do usuario deve ser informada")
        Date dataCriacao
) {
}
