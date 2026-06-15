package br.com.alura.adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.adopet.api.dto.ocorrencia.CreateOcorrenciaRequestDto;
import br.com.alura.adopet.api.dto.ocorrencia.OcorrenciaMapper;
import br.com.alura.adopet.api.dto.ocorrencia.OcorrenciaResponseDto;
import br.com.alura.adopet.api.dto.ocorrencia.UpdateOcorrenciaRequestDto;
import br.com.alura.adopet.api.model.Animal;
import br.com.alura.adopet.api.model.Ocorrencia;
import br.com.alura.adopet.api.model.Usuario;
import br.com.alura.adopet.api.model.Voluntario;
import br.com.alura.adopet.api.repository.AnimalRepository;
import br.com.alura.adopet.api.repository.OcorrenciaRepository;
import br.com.alura.adopet.api.repository.UsuarioRepository;
import br.com.alura.adopet.api.repository.VoluntarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final AnimalRepository animalRepository;
    private final VoluntarioRepository voluntarioRepository;
    private final UsuarioRepository usuarioRepository;

    public OcorrenciaService(
            OcorrenciaRepository ocorrenciaRepository,
            AnimalRepository animalRepository,
            VoluntarioRepository voluntarioRepository,
            UsuarioRepository usuarioRepository
    ) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.animalRepository = animalRepository;
        this.voluntarioRepository = voluntarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public OcorrenciaResponseDto criar(CreateOcorrenciaRequestDto request) {
        Animal animal = animalRepository.findById(request.animalId())
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        Ocorrencia ocorrencia = OcorrenciaMapper.toEntity(request);
        ocorrencia.setAnimal(animal);

        if (request.responsavelId() != null) {
            Voluntario responsavel = voluntarioRepository.findById(request.responsavelId())
                    .orElseThrow(() -> new IllegalArgumentException("Voluntário responsável não encontrado."));
            ocorrencia.setResponsavel(responsavel);
        }

        if (request.criadoPorId() != null) {
            Usuario usuario = usuarioRepository.findById(request.criadoPorId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário criador não encontrado."));
            ocorrencia.setCriadoPor(usuario);
        }

        Ocorrencia salva = ocorrenciaRepository.save(ocorrencia);
        return OcorrenciaMapper.toResponse(salva);
    }

    @Transactional(readOnly = true)
    public OcorrenciaResponseDto buscarPorId(UUID id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada."));

        return OcorrenciaMapper.toResponse(ocorrencia);
    }

    @Transactional(readOnly = true)
    public List<OcorrenciaResponseDto> listar() {
        return ocorrenciaRepository.findAll()
                .stream()
                .map(OcorrenciaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<OcorrenciaResponseDto> listarPorAnimal(UUID animalId) {
        return ocorrenciaRepository.findByAnimalId(animalId)
                .stream()
                .map(OcorrenciaMapper::toResponse)
                .toList();
    }

    @Transactional
    public OcorrenciaResponseDto atualizar(UUID id, UpdateOcorrenciaRequestDto request) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada."));

        OcorrenciaMapper.updateEntity(ocorrencia, request);

        if (request.animalId() != null) {
            Animal animal = animalRepository.findById(request.animalId())
                    .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));
            ocorrencia.setAnimal(animal);
        }

        if (request.responsavelId() != null) {
            Voluntario responsavel = voluntarioRepository.findById(request.responsavelId())
                    .orElseThrow(() -> new IllegalArgumentException("Voluntário responsável não encontrado."));
            ocorrencia.setResponsavel(responsavel);
        }

        Ocorrencia atualizada = ocorrenciaRepository.save(ocorrencia);
        return OcorrenciaMapper.toResponse(atualizada);
    }

    @Transactional
    public void deletar(UUID id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada."));

        ocorrenciaRepository.delete(ocorrencia);
    }
}
