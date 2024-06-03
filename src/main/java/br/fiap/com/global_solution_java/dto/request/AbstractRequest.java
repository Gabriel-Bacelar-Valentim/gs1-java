package br.fiap.com.global_solution_java.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AbstractRequest(
        @Positive(message = "O id precisa ser positivo")
        @NotNull(message = "O id precisa ser informado")
        Long id
) {
}
