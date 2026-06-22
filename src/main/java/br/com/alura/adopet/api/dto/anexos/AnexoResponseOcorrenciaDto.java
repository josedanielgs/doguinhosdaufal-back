package br.com.alura.adopet.api.dto.anexos;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnexoResponseOcorrenciaDto(
        UUID id,
        String nomeOriginal,
        String contentType,
        Long tamanho,
        LocalDateTime dataCriacao,
        UUID ocorrenciaId
) {
}