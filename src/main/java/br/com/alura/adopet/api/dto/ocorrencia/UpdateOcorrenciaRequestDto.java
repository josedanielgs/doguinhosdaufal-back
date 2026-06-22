package br.com.alura.adopet.api.dto.ocorrencia;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.alura.adopet.api.model.Anexo;
import br.com.alura.adopet.api.model.TipoOcorrencia;

public record UpdateOcorrenciaRequestDto(
        TipoOcorrencia tipo,
        Boolean programada,
        LocalDate data,
        String descricao,
        String acoesImediatas,
        String acoesResolucao,
        UUID animalId,
        UUID responsavelId,
        List<Anexo> anexos
) {
}
