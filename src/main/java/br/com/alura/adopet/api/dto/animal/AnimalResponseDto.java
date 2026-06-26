package br.com.alura.adopet.api.dto.animal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import br.com.alura.adopet.api.model.EspecieAnimal;
import br.com.alura.adopet.api.model.SexoAnimal;
import br.com.alura.adopet.api.model.StatusAnimal;
import br.com.alura.adopet.api.model.PorteAnimal;
import br.com.alura.adopet.api.model.PelagemAnimal;

public record AnimalResponseDto(
        UUID id,
        String nome,
        SexoAnimal sexo,
        EspecieAnimal especie,
        PorteAnimal porte,
        PelagemAnimal pelagem,
        String raca,
        LocalDate dataNascimento,
        String descricao,
        Boolean castrado,
        StatusAnimal status,
        UUID criadoPorId,
        LocalDateTime dataCriacao
) {
}
