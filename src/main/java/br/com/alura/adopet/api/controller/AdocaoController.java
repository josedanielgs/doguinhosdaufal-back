package br.com.alura.adopet.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.adopet.api.dto.adocao.AdocaoResponseDto;
import br.com.alura.adopet.api.dto.adocao.CreateAdocaoRequestDto;
import br.com.alura.adopet.api.dto.adocao.UpdateAdocaoRequestDto;
import br.com.alura.adopet.api.service.AdocaoService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController {

    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    @PostMapping
    public ResponseEntity<AdocaoResponseDto> criar(@Valid @RequestBody CreateAdocaoRequestDto request) {
        AdocaoResponseDto response = adocaoService.criar(request);

        return ResponseEntity
                .created(URI.create("/api/adocoes/" + response.id()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdocaoResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(adocaoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<AdocaoResponseDto>> listar() {
        return ResponseEntity.ok(adocaoService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdocaoResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAdocaoRequestDto request
    ) {
        return ResponseEntity.ok(adocaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        adocaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}