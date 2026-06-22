package br.com.alura.adopet.api.dto.adocao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public record AdocaoResponseDto(
        UUID id,
        LocalDate dataAdocao,
        String observacoes,
        UUID animalId,
        UUID adotanteId,
        UUID criadoPorId,
        LocalDateTime dataCriacao
) {
}
