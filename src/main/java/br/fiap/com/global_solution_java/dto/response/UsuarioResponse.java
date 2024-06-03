package br.fiap.com.global_solution_java.dto.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record UsuarioResponse(
        Long id,

        String nomeUsuario,

        String email,

        String cargo,

        Date dataCriacao
) {
}
