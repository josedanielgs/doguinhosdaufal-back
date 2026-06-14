package br.com.alura.adopet.api.dto.animal;

import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

import br.com.alura.adopet.api.model.EspecieAnimal;
import br.com.alura.adopet.api.model.SexoAnimal;
import br.com.alura.adopet.api.model.StatusAnimal;

public record UpdateAnimalRequestDto(
        @Size(max = 120)
        String nome,

        @Size(max = 500)
        String fotoUrl,

        SexoAnimal sexo,

        EspecieAnimal especie,

        @Size(max = 80)
        String raca,

        LocalDate dataNascimento,

        String descricao,

        Boolean castrado,

        StatusAnimal status,

        UUID criadoPorId
) {
}
