package br.fiap.com.global_solution_java.dto.response;

import lombok.Builder;

@Builder
public record ParceiroResponse(
        Long id,

        String nomeParceiro,

        String tipoParceiro,

        String contato,

        String email,

        String telefone
) {
}
