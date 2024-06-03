package br.fiap.com.global_solution_java.dto.request;


import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record SensorRequest(

        @NotNull(message = "O tipo do sensor deve ser informado")
        String tipoSensor,

        @NotNull(message = "A descricao do sensor deve ser informada")
        String descricao,

        @NotNull(message = "A localizacao do sensor deve ser informada")
        String localizacao,

        @NotNull(message = "a data de instalacao do sensor deve ser informada")
        Date dataInstalacao,

        @NotNull
        AbstractRequest projeto
) {
}
