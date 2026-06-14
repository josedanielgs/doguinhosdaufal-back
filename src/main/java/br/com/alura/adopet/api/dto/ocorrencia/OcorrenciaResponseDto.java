package br.com.alura.adopet.api.dto.ocorrencia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.alura.adopet.api.model.TipoOcorrencia;

public record OcorrenciaResponseDto(
        UUID id,
        TipoOcorrencia tipo,
        Boolean programada,
        LocalDate data,
        String descricao,
        String acoesImediatas,
        String acoesResolucao,
        UUID animalId,
        UUID responsavelId,
        UUID criadoPorId,
        LocalDateTime dataCriacao
) {
}
