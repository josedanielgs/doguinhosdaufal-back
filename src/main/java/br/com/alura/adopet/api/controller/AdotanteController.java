package br.com.alura.adopet.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.adopet.api.dto.adotante.AdotanteResponseDto;
import br.com.alura.adopet.api.dto.adotante.CreateAdotanteRequestDto;
import br.com.alura.adopet.api.dto.adotante.UpdateAdotanteRequestDto;
import br.com.alura.adopet.api.service.AdotanteService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/adotantes")
public class AdotanteController {

    private final AdotanteService adotanteService;

    public AdotanteController(AdotanteService adotanteService) {
        this.adotanteService = adotanteService;
    }

    @PostMapping
    public ResponseEntity<AdotanteResponseDto> criar(@Valid @RequestBody CreateAdotanteRequestDto request) {
        AdotanteResponseDto response = adotanteService.criar(request);

        return ResponseEntity
                .created(URI.create("/api/adotantes/" + response.id()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdotanteResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(adotanteService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AdotanteResponseDto>> listar() {
        return ResponseEntity.ok(adotanteService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdotanteResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAdotanteRequestDto request
    ) {
        return ResponseEntity.ok(adotanteService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        adotanteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
