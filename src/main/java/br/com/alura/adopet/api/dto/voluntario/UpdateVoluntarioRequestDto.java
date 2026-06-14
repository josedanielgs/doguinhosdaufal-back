package br.com.alura.adopet.api.dto.voluntario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateVoluntarioRequestDto(
        @Size(max = 120)
        String nome,

        @Size(max = 500)
        String fotoUrl,

        String descricao,

        @Size(max = 20)
        String telefone,

        @Email
        @Size(max = 150)
        String email,

        Boolean ativo
) {
}
