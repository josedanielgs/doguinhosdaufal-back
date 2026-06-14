package br.com.alura.adopet.api.dto.adotante;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdotanteResponseDto(
        UUID id,
        String nome,
        String cpfRg,
        String telefone,
        String email,
        String endereco,
        String observacoes,
        LocalDateTime dataCadastro
) {
}
