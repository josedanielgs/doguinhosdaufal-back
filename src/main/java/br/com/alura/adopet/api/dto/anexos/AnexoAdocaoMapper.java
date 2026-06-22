package br.com.alura.adopet.api.dto.anexos;

import br.com.alura.adopet.api.model.Anexo;

public class AnexoAdocaoMapper {

    private AnexoAdocaoMapper() {
    }

    public static AnexoResponseAdocaoDto toResponse(Anexo anexo) {
        return new AnexoResponseAdocaoDto(
                anexo.getId(),
                anexo.getNomeOriginal(),
                anexo.getContentType(),
                anexo.getTamanho(),
                anexo.getDataCriacao(),
                anexo.getAdocao().getId()
        );
    }
}