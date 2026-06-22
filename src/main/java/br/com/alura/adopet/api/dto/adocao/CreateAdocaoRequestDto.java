package br.com.alura.adopet.api.dto.adocao;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateAdocaoRequestDto(
        @NotNull
        LocalDate dataAdocao,

        String observacoes,

        @NotNull
        UUID animalId,

        @NotNull
        UUID adotanteId,

        UUID criadoPorId,

        @NotNull
        UUID ocorrenciaId
) {
}
