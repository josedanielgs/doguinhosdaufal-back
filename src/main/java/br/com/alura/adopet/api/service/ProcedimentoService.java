package br.com.alura.adopet.api.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alura.adopet.api.dto.procedimento.CreateProcedimentoRequestDto;
import br.com.alura.adopet.api.dto.procedimento.ProcedimentoMapper;
import br.com.alura.adopet.api.dto.procedimento.ProcedimentoResponseDto;
import br.com.alura.adopet.api.dto.procedimento.UpdateProcedimentoRequestDto;
import br.com.alura.adopet.api.model.Ocorrencia;
import br.com.alura.adopet.api.model.Procedimento;
import br.com.alura.adopet.api.repository.OcorrenciaRepository;
import br.com.alura.adopet.api.repository.ProcedimentoRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public ProcedimentoService(
            ProcedimentoRepository procedimentoRepository,
            OcorrenciaRepository ocorrenciaRepository
    ) {
        this.procedimentoRepository = procedimentoRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    @Transactional
    public ProcedimentoResponseDto criar(CreateProcedimentoRequestDto request) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(request.ocorrenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada."));

        Procedimento procedimento = ProcedimentoMapper.toEntity(request);
        procedimento.setOcorrencia(ocorrencia);

        Procedimento salvo = procedimentoRepository.save(procedimento);
        return ProcedimentoMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public ProcedimentoResponseDto buscarPorId(UUID id) {
        Procedimento procedimento = procedimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedimento não encontrado."));

        return ProcedimentoMapper.toResponse(procedimento);
    }

    @Transactional(readOnly = true)
    public List<ProcedimentoResponseDto> listar() {
        return procedimentoRepository.findAll()
                .stream()
                .map(ProcedimentoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ProcedimentoResponseDto> listarPorOcorrencia(UUID ocorrenciaId) {
        return procedimentoRepository.findByOcorrenciaId(ocorrenciaId)
                .stream()
                .map(ProcedimentoMapper::toResponse)
                .toList();
    }

    @Transactional
    public ProcedimentoResponseDto atualizar(UUID id, UpdateProcedimentoRequestDto request) {
        Procedimento procedimento = procedimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedimento não encontrado."));

        ProcedimentoMapper.updateEntity(procedimento, request);

        if (request.ocorrenciaId() != null) {
            Ocorrencia ocorrencia = ocorrenciaRepository.findById(request.ocorrenciaId())
                    .orElseThrow(() -> new IllegalArgumentException("Ocorrência não encontrada."));
            procedimento.setOcorrencia(ocorrencia);
        }

        Procedimento atualizado = procedimentoRepository.save(procedimento);
        return ProcedimentoMapper.toResponse(atualizado);
    }

    @Transactional
    public void deletar(UUID id) {
        Procedimento procedimento = procedimentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Procedimento não encontrado."));

        procedimentoRepository.delete(procedimento);
    }
}