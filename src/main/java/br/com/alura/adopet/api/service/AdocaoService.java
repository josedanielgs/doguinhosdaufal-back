package br.com.alura.adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.adopet.api.dto.adocao.AdocaoMapper;
import br.com.alura.adopet.api.dto.adocao.AdocaoResponseDto;
import br.com.alura.adopet.api.dto.adocao.CreateAdocaoRequestDto;
import br.com.alura.adopet.api.dto.adocao.UpdateAdocaoRequestDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Adotante;
import br.com.alura.adopet.api.model.Animal;
import br.com.alura.adopet.api.model.Ocorrencia;
import br.com.alura.adopet.api.model.StatusAnimal;
import br.com.alura.adopet.api.model.Usuario;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.AdotanteRepository;
import br.com.alura.adopet.api.repository.AnimalRepository;
import br.com.alura.adopet.api.repository.OcorrenciaRepository;
import br.com.alura.adopet.api.repository.UsuarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;
    private final AdotanteRepository adotanteRepository;
    private final UsuarioRepository usuarioRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public AdocaoService(
            AdocaoRepository adocaoRepository,
            AnimalRepository animalRepository,
            AdotanteRepository adotanteRepository,
            UsuarioRepository usuarioRepository,
            OcorrenciaRepository ocorrenciaRepository
    ) {
        this.adocaoRepository = adocaoRepository;
        this.animalRepository = animalRepository;
        this.adotanteRepository = adotanteRepository;
        this.usuarioRepository = usuarioRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    @Transactional
    public AdocaoResponseDto criar(CreateAdocaoRequestDto request) {
        if (adocaoRepository.existsByAnimalId(request.animalId())) {
            throw new IllegalArgumentException("Esse animal já possui adoção registrada.");
        }

        Animal animal = animalRepository.findById(request.animalId())
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        Adotante adotante = adotanteRepository.findById(request.adotanteId())
                .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado."));

        Ocorrencia ocorrencia = ocorrenciaRepository.findById(request.ocorrenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada."));

        Adocao adocao = AdocaoMapper.toEntity(request);
        adocao.setAnimal(animal);
        adocao.setAdotante(adotante);
        adocao.setOcorrencia(ocorrencia);

        if (request.criadoPorId() != null) {
            Usuario usuario = usuarioRepository.findById(request.criadoPorId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário criador não encontrado."));
            adocao.setCriadoPor(usuario);
        }

        animal.setStatus(StatusAnimal.ADOTADO);

        Adocao salva = adocaoRepository.save(adocao);
        return AdocaoMapper.toResponse(salva);
    }

    @Transactional(readOnly = true)
    public AdocaoResponseDto buscarPorId(UUID id) {
        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada."));

        return AdocaoMapper.toResponse(adocao);
    }

    @Transactional(readOnly = true)
    public List<AdocaoResponseDto> listar() {
        return adocaoRepository.findAll()
                .stream()
                .map(AdocaoMapper::toResponse)
                .toList();
    }

    @Transactional
    public AdocaoResponseDto atualizar(UUID id, UpdateAdocaoRequestDto request) {
        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada."));

        AdocaoMapper.updateEntity(adocao, request);

        if (request.animalId() != null) {
            Animal animal = animalRepository.findById(request.animalId())
                    .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));
            adocao.setAnimal(animal);
        }

        if (request.adotanteId() != null) {
            Adotante adotante = adotanteRepository.findById(request.adotanteId())
                    .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado."));
            adocao.setAdotante(adotante);
        }

        Adocao atualizada = adocaoRepository.save(adocao);
        return AdocaoMapper.toResponse(atualizada);
    }

    @Transactional
    public void deletar(UUID id) {
        Adocao adocao = adocaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada."));

        Animal animal = adocao.getAnimal();
        if (animal != null && animal.getStatus() == StatusAnimal.ADOTADO) {
            animal.setStatus(StatusAnimal.DISPONIVEL_ADOCAO);
        }

        adocaoRepository.delete(adocao);
    }
}
