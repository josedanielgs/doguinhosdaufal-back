package br.com.alura.adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.adopet.api.dto.animal.AnimalMapper;
import br.com.alura.adopet.api.dto.animal.AnimalResponseDto;
import br.com.alura.adopet.api.dto.animal.CreateAnimalRequestDto;
import br.com.alura.adopet.api.dto.animal.UpdateAnimalRequestDto;
import br.com.alura.adopet.api.model.Animal;
import br.com.alura.adopet.api.model.Usuario;
import br.com.alura.adopet.api.repository.AnimalRepository;
import br.com.alura.adopet.api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final UsuarioRepository usuarioRepository;

    public AnimalService(AnimalRepository animalRepository, UsuarioRepository usuarioRepository) {
        this.animalRepository = animalRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public AnimalResponseDto criar(CreateAnimalRequestDto request) {
        Animal animal = AnimalMapper.toEntity(request);

        if (request.criadoPorId() != null) {
            Usuario usuario = usuarioRepository.findById(request.criadoPorId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário criador não encontrado."));
            animal.setCriadoPor(usuario);
        }

        Animal salvo = animalRepository.save(animal);
        return AnimalMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public AnimalResponseDto buscarPorId(UUID id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        return AnimalMapper.toResponse(animal);
    }

    @Transactional(readOnly = true)
    public List<AnimalResponseDto> listar() {
        return animalRepository.findAll()
                .stream()
                .map(AnimalMapper::toResponse)
                .toList();
    }

    @Transactional
    public AnimalResponseDto atualizar(UUID id, UpdateAnimalRequestDto request) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        AnimalMapper.updateEntity(animal, request);

        if (request.criadoPorId() != null) {
            Usuario usuario = usuarioRepository.findById(request.criadoPorId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário criador não encontrado."));
            animal.setCriadoPor(usuario);
        }

        Animal atualizado = animalRepository.save(animal);
        return AnimalMapper.toResponse(atualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        animalRepository.delete(animal);
    }
}
