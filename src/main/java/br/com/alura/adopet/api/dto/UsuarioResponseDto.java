package br.com.alura.adopet.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.alura.adopet.api.model.PerfilUsuario;

public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email,
        PerfilUsuario perfil,
        Boolean ativo,
        LocalDateTime dataCriacao
) {
}
