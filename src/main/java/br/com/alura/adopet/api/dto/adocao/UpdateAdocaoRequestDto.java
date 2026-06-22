package br.com.alura.adopet.api.dto.adocao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.alura.adopet.api.model.Anexo;

public record UpdateAdocaoRequestDto(
        LocalDate dataAdocao,
        String observacoes,
        List<Anexo> anexos,
        UUID animalId,
        UUID adotanteId
) {
}