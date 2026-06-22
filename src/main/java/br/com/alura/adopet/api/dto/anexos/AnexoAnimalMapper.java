package br.com.alura.adopet.api.dto.anexos;

import br.com.alura.adopet.api.model.Anexo;

public class AnexoAnimalMapper {

    private AnexoAnimalMapper() {
    }

    public static AnexoResponseAnimalDto toResponse(Anexo anexo) {
        return new AnexoResponseAnimalDto(
                anexo.getId(),
                anexo.getNomeOriginal(),
                anexo.getContentType(),
                anexo.getTamanho(),
                anexo.getDataCriacao(),
                anexo.getAnimal().getId()
        );
    }
}