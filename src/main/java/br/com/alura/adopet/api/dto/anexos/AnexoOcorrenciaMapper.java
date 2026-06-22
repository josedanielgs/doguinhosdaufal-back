package br.com.alura.adopet.api.dto.anexos;

import br.com.alura.adopet.api.model.Anexo;

public class AnexoOcorrenciaMapper {

    private AnexoOcorrenciaMapper() {
    }

    public static AnexoResponseOcorrenciaDto toResponse(Anexo anexo) {
        return new AnexoResponseOcorrenciaDto(
                anexo.getId(),
                anexo.getNomeOriginal(),
                anexo.getContentType(),
                anexo.getTamanho(),
                anexo.getDataCriacao(),
                anexo.getOcorrencia().getId()
        );
    }
}