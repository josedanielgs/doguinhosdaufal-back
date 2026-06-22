package br.com.alura.adopet.api.dto.anexos;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnexoResponseAdocaoDto(
        UUID id,
        String nomeOriginal,
        String contentType,
        Long tamanho,
        LocalDateTime dataCriacao,
        UUID adocaoId
) {
}