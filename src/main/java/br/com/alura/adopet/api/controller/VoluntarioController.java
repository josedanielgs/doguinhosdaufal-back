package br.com.alura.adopet.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.adopet.api.dto.voluntario.CreateVoluntarioRequestDto;
import br.com.alura.adopet.api.dto.voluntario.UpdateVoluntarioRequestDto;
import br.com.alura.adopet.api.dto.voluntario.VoluntarioResponseDto;
import br.com.alura.adopet.api.service.VoluntarioService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {

    private final VoluntarioService voluntarioService;

    public VoluntarioController(VoluntarioService voluntarioService) {
        this.voluntarioService = voluntarioService;
    }

    @PostMapping
    public ResponseEntity<VoluntarioResponseDto> criar(@Valid @RequestBody CreateVoluntarioRequestDto request) {
        VoluntarioResponseDto response = voluntarioService.criar(request);

        return ResponseEntity
                .created(URI.create("/api/voluntarios/" + response.id()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoluntarioResponseDto> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(voluntarioService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<VoluntarioResponseDto>> listar() {
        return ResponseEntity.ok(voluntarioService.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VoluntarioResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateVoluntarioRequestDto request
    ) {
        return ResponseEntity.ok(voluntarioService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        voluntarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
