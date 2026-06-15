package br.com.alura.adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.adopet.api.dto.adotante.AdotanteMapper;
import br.com.alura.adopet.api.dto.adotante.AdotanteResponseDto;
import br.com.alura.adopet.api.dto.adotante.CreateAdotanteRequestDto;
import br.com.alura.adopet.api.dto.adotante.UpdateAdotanteRequestDto;
import br.com.alura.adopet.api.model.Adotante;
import br.com.alura.adopet.api.repository.AdotanteRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AdotanteService {

    private final AdotanteRepository adotanteRepository;

    public AdotanteService(AdotanteRepository adotanteRepository) {
        this.adotanteRepository = adotanteRepository;
    }

    @Transactional
    public AdotanteResponseDto criar(CreateAdotanteRequestDto request) {
        Adotante adotante = AdotanteMapper.toEntity(request);
        Adotante salvo = adotanteRepository.save(adotante);
        return AdotanteMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public AdotanteResponseDto buscarPorId(UUID id) {
        Adotante adotante = adotanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado."));
        return AdotanteMapper.toResponse(adotante);
    }

    @Transactional(readOnly = true)
    public List<AdotanteResponseDto> listar() {
        return adotanteRepository.findAll()
                .stream()
                .map(AdotanteMapper::toResponse)
                .toList();
    }

    @Transactional
    public AdotanteResponseDto atualizar(UUID id, UpdateAdotanteRequestDto request) {
        Adotante adotante = adotanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado."));

        AdotanteMapper.updateEntity(adotante, request);

        Adotante atualizado = adotanteRepository.save(adotante);
        return AdotanteMapper.toResponse(atualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Adotante adotante = adotanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Adotante não encontrado."));

        adotanteRepository.delete(adotante);
    }
}
