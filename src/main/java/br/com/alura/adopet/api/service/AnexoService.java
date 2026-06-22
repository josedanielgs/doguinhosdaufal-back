package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.anexos.AnexoResponseOcorrenciaDto;
import br.com.alura.adopet.api.dto.animal.AnimalResponseDto;
import br.com.alura.adopet.api.dto.anexos.AnexoAdocaoMapper;
import br.com.alura.adopet.api.dto.anexos.AnexoAnimalMapper;
import br.com.alura.adopet.api.dto.anexos.AnexoOcorrenciaMapper;
import br.com.alura.adopet.api.dto.anexos.AnexoResponseAdocaoDto;
import br.com.alura.adopet.api.dto.anexos.AnexoResponseAnimalDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Anexo;
import br.com.alura.adopet.api.model.Animal;
import br.com.alura.adopet.api.model.Ocorrencia;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.AnexoRepository;
import br.com.alura.adopet.api.repository.AnimalRepository;
import br.com.alura.adopet.api.repository.OcorrenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class AnexoService {

    private static final List<String> CONTENT_TYPES_PERMITIDOS = List.of(
            "image/jpeg",
            "image/png",
            "application/pdf"
    );

    private static final long TAMANHO_MAXIMO_BYTES = 5 * 1024 * 1024; // 5 MB

    private final AnexoRepository anexoRepository;
    private final OcorrenciaRepository ocorrenciaRepository;
    private final AnimalRepository animalRepository;
    private final AdocaoRepository adocaoRepository;

    public AnexoService(AnexoRepository anexoRepository, OcorrenciaRepository ocorrenciaRepository, AnimalRepository animalRepository, AdocaoRepository adocaoRepository) {
        this.anexoRepository = anexoRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.animalRepository = animalRepository;
        this.adocaoRepository = adocaoRepository;
    }

    @Transactional
    public AnexoResponseOcorrenciaDto uploadParaOcorrencia(UUID ocorrenciaId, MultipartFile arquivo) throws IOException {
        if (arquivo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo vazio.");
        }

        if (arquivo.getSize() > TAMANHO_MAXIMO_BYTES) {
            throw new IllegalArgumentException("Arquivo excede o tamanho máximo permitido.");
        }

        String contentType = arquivo.getContentType();
        if (contentType == null || !CONTENT_TYPES_PERMITIDOS.contains(contentType)) {
            throw new IllegalArgumentException("Tipo de arquivo não permitido.");
        }

        Ocorrencia ocorrencia = ocorrenciaRepository.findById(ocorrenciaId)
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada."));

        Anexo anexo = new Anexo();
        anexo.setNomeOriginal(arquivo.getOriginalFilename());
        anexo.setNomeArmazenado(UUID.randomUUID() + "_" + arquivo.getOriginalFilename());
        anexo.setContentType(contentType);
        anexo.setTamanho(arquivo.getSize());
        anexo.setDados(arquivo.getBytes());
        anexo.setOcorrencia(ocorrencia);

        Anexo salvo = anexoRepository.save(anexo);
        return AnexoOcorrenciaMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public List<AnexoResponseOcorrenciaDto> listarPorOcorrencia(UUID ocorrenciaId) {
        return anexoRepository.findByOcorrenciaId(ocorrenciaId)
                .stream()
                .map(AnexoOcorrenciaMapper::toResponse)
                .toList();
    }

    @Transactional
    public AnexoResponseAnimalDto uploadParaAnimal(UUID animalId, MultipartFile arquivo) throws IOException {
        if (arquivo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo vazio.");
        }

        if (arquivo.getSize() > TAMANHO_MAXIMO_BYTES) {
            throw new IllegalArgumentException("Arquivo excede o tamanho máximo permitido.");
        }

        String contentType = arquivo.getContentType();
        if (contentType == null || !CONTENT_TYPES_PERMITIDOS.contains(contentType)) {
            throw new IllegalArgumentException("Tipo de arquivo não permitido.");
        }

        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado."));

        Anexo anexo = new Anexo();
        anexo.setNomeOriginal(arquivo.getOriginalFilename());
        anexo.setNomeArmazenado(UUID.randomUUID() + "_" + arquivo.getOriginalFilename());
        anexo.setContentType(contentType);
        anexo.setTamanho(arquivo.getSize());
        anexo.setDados(arquivo.getBytes());
        anexo.setAnimal(animal);

        Anexo salvo = anexoRepository.save(anexo);
        return AnexoAnimalMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public List<AnexoResponseAnimalDto> listarPorAnimal(UUID animalId) {
        return anexoRepository.findByAnimalId(animalId)
                .stream()
                .map(AnexoAnimalMapper::toResponse)
                .toList();
    }

    @Transactional
    public AnexoResponseAdocaoDto uploadParaAdocao(UUID adocaoId, MultipartFile arquivo) throws IOException {
        if (arquivo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo vazio.");
        }

        if (arquivo.getSize() > TAMANHO_MAXIMO_BYTES) {
            throw new IllegalArgumentException("Arquivo excede o tamanho máximo permitido.");
        }

        String contentType = arquivo.getContentType();
        if (contentType == null || !CONTENT_TYPES_PERMITIDOS.contains(contentType)) {
            throw new IllegalArgumentException("Tipo de arquivo não permitido.");
        }

        Adocao adocao = adocaoRepository.findById(adocaoId)
                .orElseThrow(() -> new IllegalArgumentException("Adoção não encontrada."));

        Anexo anexo = new Anexo();
        anexo.setNomeOriginal(arquivo.getOriginalFilename());
        anexo.setNomeArmazenado(UUID.randomUUID() + "_" + arquivo.getOriginalFilename());
        anexo.setContentType(contentType);
        anexo.setTamanho(arquivo.getSize());
        anexo.setDados(arquivo.getBytes());
        anexo.setAdocao(adocao);

        Anexo salvo = anexoRepository.save(anexo);
        return AnexoAdocaoMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public List<AnexoResponseAdocaoDto> listarPorAdocao(UUID adocaoId) {
        return anexoRepository.findByAdocaoId(adocaoId)
                .stream()
                .map(AnexoAdocaoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public Anexo buscarEntidade(UUID id) {
        return anexoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Anexo não encontrado."));
    }

    @Transactional
    public void deletar(UUID id) {
        Anexo anexo = buscarEntidade(id);
        anexoRepository.delete(anexo);
    }
}
