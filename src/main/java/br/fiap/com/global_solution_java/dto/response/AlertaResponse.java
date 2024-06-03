package br.fiap.com.global_solution_java.dto.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record AlertaResponse(

        Long id,

        String tipoAlerta,

        String descricao,

        Date dataGeracao,

        HabitatResponse habitat,

        UsuarioResponse usuario

) {
}
