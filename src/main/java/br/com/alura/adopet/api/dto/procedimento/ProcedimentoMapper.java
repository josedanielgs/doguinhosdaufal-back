package br.com.alura.adopet.api.dto.procedimento;

import br.com.alura.adopet.api.model.Procedimento;

public class ProcedimentoMapper {

    private ProcedimentoMapper() {
    }

    public static Procedimento toEntity(CreateProcedimentoRequestDto request) {
        Procedimento procedimento = new Procedimento();
        procedimento.setTipo(request.tipo());
        procedimento.setData(request.data());
        procedimento.setDescricao(request.descricao());
        procedimento.setProfissional(request.profissional());
        procedimento.setLocal(request.local());
        procedimento.setObservacao(request.observacao());
        procedimento.setAnexoUrl(request.anexoUrl());
        return procedimento;
    }

    public static void updateEntity(Procedimento procedimento, UpdateProcedimentoRequestDto request) {
        if (request.tipo() != null) {
            procedimento.setTipo(request.tipo());
        }
        if (request.data() != null) {
            procedimento.setData(request.data());
        }
        if (request.descricao() != null) {
            procedimento.setDescricao(request.descricao());
        }
        if (request.profissional() != null) {
            procedimento.setProfissional(request.profissional());
        }
        if (request.local() != null) {
            procedimento.setLocal(request.local());
        }
        if (request.observacao() != null) {
            procedimento.setObservacao(request.observacao());
        }
        if (request.anexoUrl() != null) {
            procedimento.setAnexoUrl(request.anexoUrl());
        }
    }

    public static ProcedimentoResponseDto toResponse(Procedimento procedimento) {
        return new ProcedimentoResponseDto(
                procedimento.getId(),
                procedimento.getTipo(),
                procedimento.getData(),
                procedimento.getDescricao(),
                procedimento.getProfissional(),
                procedimento.getLocal(),
                procedimento.getObservacao(),
                procedimento.getAnexoUrl(),
                procedimento.getOcorrencia() != null ? procedimento.getOcorrencia().getId() : null
        );
    }
}
