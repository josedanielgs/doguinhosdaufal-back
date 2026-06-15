package br.com.alura.adopet.api.dto.adocao;

import br.com.alura.adopet.api.model.Adocao;

public class AdocaoMapper {

    private AdocaoMapper() {
    }

    public static Adocao toEntity(CreateAdocaoRequestDto request) {
        Adocao adocao = new Adocao();
        adocao.setDataAdocao(request.dataAdocao());
        adocao.setTermoUrl(request.termoUrl());
        adocao.setObservacoes(request.observacoes());
        return adocao;
    }

    public static void updateEntity(Adocao adocao, UpdateAdocaoRequestDto request) {
        if (request.dataAdocao() != null) {
            adocao.setDataAdocao(request.dataAdocao());
        }
        if (request.termoUrl() != null) {
            adocao.setTermoUrl(request.termoUrl());
        }
        if (request.observacoes() != null) {
            adocao.setObservacoes(request.observacoes());
        }
    }

    public static AdocaoResponseDto toResponse(Adocao adocao) {
        return new AdocaoResponseDto(
                adocao.getId(),
                adocao.getDataAdocao(),
                adocao.getTermoUrl(),
                adocao.getObservacoes(),
                adocao.getAnimal() != null ? adocao.getAnimal().getId() : null,
                adocao.getAdotante() != null ? adocao.getAdotante().getId() : null,
                adocao.getCriadoPor() != null ? adocao.getCriadoPor().getId() : null,
                adocao.getDataCriacao()
        );
    }
}