package br.com.alura.adopet.api.dto.procedimento;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

import br.com.alura.adopet.api.model.TipoProcedimento;

public record CreateProcedimentoRequestDto(
        @NotNull
        TipoProcedimento tipo,

        @NotNull
        LocalDate data,

        String descricao,
        String profissional,
        String local,
        String observacao,
        String anexoUrl,

        @NotNull
        UUID ocorrenciaId
) {
}
