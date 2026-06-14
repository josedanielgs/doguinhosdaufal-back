package br.com.alura.adopet.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record VoluntarioResponseDto(
        UUID id,
        String nome,
        String fotoUrl,
        String descricao,
        String telefone,
        String email,
        Boolean ativo,
        LocalDateTime dataCriacao
) {
}