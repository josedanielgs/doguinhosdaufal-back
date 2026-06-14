package br.com.alura.adopet.api.dto.adotante;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UpdateAdotanteRequestDto(
        @Size(max = 120)
        String nome,

        @Size(max = 20)
        String cpfRg,

        @Size(max = 20)
        String telefone,

        @Email
        @Size(max = 150)
        String email,

        String endereco,

        String observacoes
) {
}
