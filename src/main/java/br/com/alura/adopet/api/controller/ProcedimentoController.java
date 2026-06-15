package br.com.alura.adopet.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.adopet.api.dto.procedimento.CreateProcedimentoRequestDto;
import br.com.alura.adopet.api.dto.procedimento.ProcedimentoResponseDto;
import br.com.alura.adopet.api.dto.procedimento.UpdateProcedimentoRequestDto;
import br.com.alura.adopet.api.service.ProcedimentoService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    @PostMapping
    public ResponseEntity<ProcedimentoResponseDto> criar(@Valid @RequestBody CreateProcedimentoRequestDto request) {
        ProcedimentoResponseDto response = procedimentoService.criar(request);

        return ResponseEntity
                .created(URI.create("/api/procedimentos/" + response.id()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcedimentoResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(procedimentoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ProcedimentoResponseDto>> listar() {
        return ResponseEntity.ok(procedimentoService.listar());
    }

    @GetMapping("/ocorrencia/{ocorrenciaId}")
    public ResponseEntity<List<ProcedimentoResponseDto>> listarPorOcorrencia(@PathVariable UUID ocorrenciaId) {
        return ResponseEntity.ok(procedimentoService.listarPorOcorrencia(ocorrenciaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcedimentoResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProcedimentoRequestDto request
    ) {
        return ResponseEntity.ok(procedimentoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        procedimentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
