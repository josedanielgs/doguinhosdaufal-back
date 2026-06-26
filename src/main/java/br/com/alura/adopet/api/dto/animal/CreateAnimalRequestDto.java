package br.com.alura.adopet.api.dto.animal;

import br.com.alura.adopet.api.model.EspecieAnimal;
import br.com.alura.adopet.api.model.SexoAnimal;
import br.com.alura.adopet.api.model.StatusAnimal;
import br.com.alura.adopet.api.model.PorteAnimal;
import br.com.alura.adopet.api.model.PelagemAnimal;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record CreateAnimalRequestDto(
        @NotBlank
        @Size(max = 120)
        String nome,

        @NotNull
        SexoAnimal sexo,

        @NotNull
        EspecieAnimal especie,

        @Size(max = 80)
        String raca,

        LocalDate dataNascimento,

        String descricao,

        Boolean castrado,

        @NotNull
        PorteAnimal porte,

        @NotNull
        PelagemAnimal pelagem,

        @NotNull
        StatusAnimal status,

        UUID criadoPorId
) {
}