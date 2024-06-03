package br.fiap.com.global_solution_java.dto.response;


import lombok.Builder;

@Builder
public record HabitatResponse(
        Long id,

        String nomeHabitat,

        String descricao,

        String localizacao,

        String status,

        ProjetoResponse projeto
) {
}
