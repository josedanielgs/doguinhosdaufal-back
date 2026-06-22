package br.com.alura.adopet.api.dto.animal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.alura.adopet.api.model.EspecieAnimal;
import br.com.alura.adopet.api.model.SexoAnimal;
import br.com.alura.adopet.api.model.StatusAnimal;

public record AnimalResponseDto(
        UUID id,
        String nome,
        SexoAnimal sexo,
        EspecieAnimal especie,
        String raca,
        LocalDate dataNascimento,
        String descricao,
        Boolean castrado,
        StatusAnimal status,
        UUID criadoPorId,
        LocalDateTime dataCriacao
) {
}
