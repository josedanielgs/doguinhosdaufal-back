package br.com.alura.adopet.api.dto.ocorrencia;

import br.com.alura.adopet.api.model.Ocorrencia;

public class OcorrenciaMapper {

    private OcorrenciaMapper() {
    }

    public static Ocorrencia toEntity(CreateOcorrenciaRequestDto request) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setTipo(request.tipo());
        ocorrencia.setProgramada(request.programada());
        ocorrencia.setData(request.data());
        ocorrencia.setDescricao(request.descricao());
        ocorrencia.setAcoesImediatas(request.acoesImediatas());
        ocorrencia.setAcoesResolucao(request.acoesResolucao());
        return ocorrencia;
    }

    public static void updateEntity(Ocorrencia ocorrencia, UpdateOcorrenciaRequestDto request) {
        if (request.tipo() != null) {
            ocorrencia.setTipo(request.tipo());
        }
        if (request.programada() != null) {
            ocorrencia.setProgramada(request.programada());
        }
        if (request.data() != null) {
            ocorrencia.setData(request.data());
        }
        if (request.descricao() != null) {
            ocorrencia.setDescricao(request.descricao());
        }
        if (request.acoesImediatas() != null) {
            ocorrencia.setAcoesImediatas(request.acoesImediatas());
        }
        if (request.acoesResolucao() != null) {
            ocorrencia.setAcoesResolucao(request.acoesResolucao());
        }
        if(request.anexos() != null) {
            ocorrencia.setAnexos(request.anexos());
        }
    }

    public static OcorrenciaResponseDto toResponse(Ocorrencia ocorrencia) {
        return new OcorrenciaResponseDto(
                ocorrencia.getId(),
                ocorrencia.getTipo(),
                ocorrencia.getProgramada(),
                ocorrencia.getData(),
                ocorrencia.getDescricao(),
                ocorrencia.getAcoesImediatas(),
                ocorrencia.getAcoesResolucao(),
                ocorrencia.getAnimal() != null ? ocorrencia.getAnimal().getId() : null,
                ocorrencia.getResponsavel() != null ? ocorrencia.getResponsavel().getId() : null,
                ocorrencia.getCriadoPor() != null ? ocorrencia.getCriadoPor().getId() : null,
                ocorrencia.getDataCriacao()
        );
    }
}
