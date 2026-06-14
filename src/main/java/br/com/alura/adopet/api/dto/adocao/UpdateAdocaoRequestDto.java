package br.com.alura.adopet.api.dto.adocao;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateAdocaoRequestDto(
        LocalDate dataAdocao,
        String termoUrl,
        String observacoes,
        UUID animalId,
        UUID adotanteId
) {
}