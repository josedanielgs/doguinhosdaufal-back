package br.com.alura.adopet.api.dto.animal;

import br.com.alura.adopet.api.model.Animal;

public class AnimalMapper {

    private AnimalMapper() {
    }

    public static Animal toEntity(CreateAnimalRequestDto request) {
        Animal animal = new Animal();
        animal.setNome(request.nome());
        animal.setFotoUrl(request.fotoUrl());
        animal.setSexo(request.sexo());
        animal.setEspecie(request.especie());
        animal.setRaca(request.raca());
        animal.setDataNascimento(request.dataNascimento());
        animal.setDescricao(request.descricao());
        animal.setCastrado(request.castrado() != null ? request.castrado() : false);
        animal.setStatus(request.status());
        return animal;
    }

    public static void updateEntity(Animal animal, UpdateAnimalRequestDto request) {
        if (request.nome() != null) {
            animal.setNome(request.nome());
        }
        if (request.fotoUrl() != null) {
            animal.setFotoUrl(request.fotoUrl());
        }
        if (request.sexo() != null) {
            animal.setSexo(request.sexo());
        }
        if (request.especie() != null) {
            animal.setEspecie(request.especie());
        }
        if (request.raca() != null) {
            animal.setRaca(request.raca());
        }
        if (request.dataNascimento() != null) {
            animal.setDataNascimento(request.dataNascimento());
        }
        if (request.descricao() != null) {
            animal.setDescricao(request.descricao());
        }
        if (request.castrado() != null) {
            animal.setCastrado(request.castrado());
        }
        if (request.status() != null) {
            animal.setStatus(request.status());
        }
    }

    public static AnimalResponseDto toResponse(Animal animal) {
        return new AnimalResponseDto(
                animal.getId(),
                animal.getNome(),
                animal.getFotoUrl(),
                animal.getSexo(),
                animal.getEspecie(),
                animal.getRaca(),
                animal.getDataNascimento(),
                animal.getDescricao(),
                animal.getCastrado(),
                animal.getStatus(),
                animal.getCriadoPor() != null ? animal.getCriadoPor().getId() : null,
                animal.getDataCriacao()
        );
    }
}