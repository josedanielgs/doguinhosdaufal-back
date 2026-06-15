package br.com.alura.adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.adopet.api.dto.voluntario.CreateVoluntarioRequestDto;
import br.com.alura.adopet.api.dto.voluntario.UpdateVoluntarioRequestDto;
import br.com.alura.adopet.api.dto.voluntario.VoluntarioMapper;
import br.com.alura.adopet.api.dto.voluntario.VoluntarioResponseDto;
import br.com.alura.adopet.api.model.Voluntario;
import br.com.alura.adopet.api.repository.VoluntarioRepository;

import java.util.List;
import java.util.UUID;

@Service
public class VoluntarioService {

    private final VoluntarioRepository voluntarioRepository;

    public VoluntarioService(VoluntarioRepository voluntarioRepository) {
        this.voluntarioRepository = voluntarioRepository;
    }

    @Transactional
    public VoluntarioResponseDto criar(CreateVoluntarioRequestDto request) {
        Voluntario voluntario = VoluntarioMapper.toEntity(request);
        Voluntario salvo = voluntarioRepository.save(voluntario);
        return VoluntarioMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public VoluntarioResponseDto buscarPorId(UUID id) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voluntário não encontrado."));
        return VoluntarioMapper.toResponse(voluntario);
    }

    @Transactional(readOnly = true)
    public List<VoluntarioResponseDto> listar() {
        return voluntarioRepository.findAll()
                .stream()
                .map(VoluntarioMapper::toResponse)
                .toList();
    }

    @Transactional
    public VoluntarioResponseDto atualizar(UUID id, UpdateVoluntarioRequestDto request) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voluntário não encontrado."));

        VoluntarioMapper.updateEntity(voluntario, request);

        Voluntario atualizado = voluntarioRepository.save(voluntario);
        return VoluntarioMapper.toResponse(atualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Voluntario voluntario = voluntarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Voluntário não encontrado."));

        voluntarioRepository.delete(voluntario);
    }
}
