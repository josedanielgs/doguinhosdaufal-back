package br.com.alura.adopet.api.dto.procedimento;

import java.time.LocalDate;
import java.util.UUID;

import br.com.alura.adopet.api.model.TipoProcedimento;

public record ProcedimentoResponseDto(
        UUID id,
        TipoProcedimento tipo,
        LocalDate data,
        String descricao,
        String profissional,
        String local,
        String observacao,
        String anexoUrl,
        UUID ocorrenciaId
) {
}