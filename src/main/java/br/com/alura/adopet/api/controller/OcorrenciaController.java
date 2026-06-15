package br.com.alura.adopet.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.adopet.api.dto.ocorrencia.CreateOcorrenciaRequestDto;
import br.com.alura.adopet.api.dto.ocorrencia.OcorrenciaResponseDto;
import br.com.alura.adopet.api.dto.ocorrencia.UpdateOcorrenciaRequestDto;
import br.com.alura.adopet.api.service.OcorrenciaService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService) {
        this.ocorrenciaService = ocorrenciaService;
    }

    @PostMapping
    public ResponseEntity<OcorrenciaResponseDto> criar(@Valid @RequestBody CreateOcorrenciaRequestDto request) {
        OcorrenciaResponseDto response = ocorrenciaService.criar(request);

        return ResponseEntity
                .created(URI.create("/api/ocorrencias/" + response.id()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(ocorrenciaService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaResponseDto>> listar() {
        return ResponseEntity.ok(ocorrenciaService.listar());
    }

    @GetMapping("/animal/{animalId}")
    public ResponseEntity<List<OcorrenciaResponseDto>> listarPorAnimal(@PathVariable UUID animalId) {
        return ResponseEntity.ok(ocorrenciaService.listarPorAnimal(animalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOcorrenciaRequestDto request
    ) {
        return ResponseEntity.ok(ocorrenciaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        ocorrenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}