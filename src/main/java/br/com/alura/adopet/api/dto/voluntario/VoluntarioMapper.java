package br.com.alura.adopet.api.dto.voluntario;

import br.com.alura.adopet.api.model.Voluntario;

public class VoluntarioMapper {

    private VoluntarioMapper() {
    }

    public static Voluntario toEntity(CreateVoluntarioRequestDto request) {
        Voluntario voluntario = new Voluntario();
        voluntario.setNome(request.nome());
        voluntario.setFotoUrl(request.fotoUrl());
        voluntario.setDescricao(request.descricao());
        voluntario.setTelefone(request.telefone());
        voluntario.setEmail(request.email());
        voluntario.setAtivo(true);
        return voluntario;
    }

    public static void updateEntity(Voluntario voluntario, UpdateVoluntarioRequestDto request) {
        if (request.nome() != null) {
            voluntario.setNome(request.nome());
        }
        if (request.fotoUrl() != null) {
            voluntario.setFotoUrl(request.fotoUrl());
        }
        if (request.descricao() != null) {
            voluntario.setDescricao(request.descricao());
        }
        if (request.telefone() != null) {
            voluntario.setTelefone(request.telefone());
        }
        if (request.email() != null) {
            voluntario.setEmail(request.email());
        }
        if (request.ativo() != null) {
            voluntario.setAtivo(request.ativo());
        }
    }

    public static VoluntarioResponseDto toResponse(Voluntario voluntario) {
        return new VoluntarioResponseDto(
                voluntario.getId(),
                voluntario.getNome(),
                voluntario.getFotoUrl(),
                voluntario.getDescricao(),
                voluntario.getTelefone(),
                voluntario.getEmail(),
                voluntario.getAtivo(),
                voluntario.getDataCriacao()
        );
    }
}