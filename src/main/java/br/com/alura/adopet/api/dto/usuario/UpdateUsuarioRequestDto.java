package br.com.alura.adopet.api.dto.usuario;

import java.time.LocalDateTime;

import br.com.alura.adopet.api.model.PerfilUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateUsuarioRequestDto(
        @Size(max = 120)
        String nome,

        @Email
        @Size(max = 150)
        String email,

        @Size(min = 6, max = 255)
        String senha,

        PerfilUsuario perfil,

        Boolean ativo,

        @Size(max = 500)
        String fotoUrl,

        String descricao,

        @Size(max = 20)
        String telefone,

        LocalDateTime dataIngresso,

        String curso
) {
}