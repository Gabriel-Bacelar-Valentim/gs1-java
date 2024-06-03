package br.fiap.com.global_solution_java.dto.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record ProjetoResponse(
        Long id,

        String nomeProjeto,

        String descricao,

        Date dataInicio,

        Date dataFim,

        UsuarioResponse usuario
) {
}
