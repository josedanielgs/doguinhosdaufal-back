package br.com.alura.adopet.api.dto.ocorrencia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

import br.com.alura.adopet.api.model.TipoOcorrencia;

public record CreateOcorrenciaRequestDto(
        @NotNull
        TipoOcorrencia tipo,

        @NotNull
        Boolean programada,

        @NotNull
        LocalDate data,

        @NotBlank
        String descricao,

        String acoesImediatas,

        String acoesResolucao,

        @NotNull
        UUID animalId,

        UUID responsavelId,

        UUID criadoPorId
) {
}
