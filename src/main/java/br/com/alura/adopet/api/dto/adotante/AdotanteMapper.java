package br.com.alura.adopet.api.dto.adotante;

import br.com.alura.adopet.api.model.Adotante;

public class AdotanteMapper {

    private AdotanteMapper() {
    }

    public static Adotante toEntity(CreateAdotanteRequestDto request) {
        Adotante adotante = new Adotante();
        adotante.setNome(request.nome());
        adotante.setCpfRg(request.cpfRg());
        adotante.setTelefone(request.telefone());
        adotante.setEmail(request.email());
        adotante.setEndereco(request.endereco());
        adotante.setObservacoes(request.observacoes());
        return adotante;
    }

    public static void updateEntity(Adotante adotante, UpdateAdotanteRequestDto request) {
        if (request.nome() != null) {
            adotante.setNome(request.nome());
        }
        if (request.cpfRg() != null) {
            adotante.setCpfRg(request.cpfRg());
        }
        if (request.telefone() != null) {
            adotante.setTelefone(request.telefone());
        }
        if (request.email() != null) {
            adotante.setEmail(request.email());
        }
        if (request.endereco() != null) {
            adotante.setEndereco(request.endereco());
        }
        if (request.observacoes() != null) {
            adotante.setObservacoes(request.observacoes());
        }
    }

    public static AdotanteResponseDto toResponse(Adotante adotante) {
        return new AdotanteResponseDto(
                adotante.getId(),
                adotante.getNome(),
                adotante.getCpfRg(),
                adotante.getTelefone(),
                adotante.getEmail(),
                adotante.getEndereco(),
                adotante.getObservacoes(),
                adotante.getDataCadastro()
        );
    }
}
