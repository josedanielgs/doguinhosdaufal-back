package br.com.alura.adopet.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.adopet.api.dto.usuario.CreateUsuarioRequestDto;
import br.com.alura.adopet.api.dto.usuario.UpdateUsuarioRequestDto;
import br.com.alura.adopet.api.dto.usuario.UsuarioResponseDto;
import br.com.alura.adopet.api.service.UsuarioService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDto> criar(@Valid @RequestBody CreateUsuarioRequestDto request) {
        UsuarioResponseDto response = usuarioService.criar(request);

        return ResponseEntity
                .created(URI.create("/api/usuarios/" + response.id()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> buscarPorId(@PathVariable UUID id) {
        UsuarioResponseDto response = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        List<UsuarioResponseDto> response = usuarioService.listar();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateUsuarioRequestDto request
    ) {
        UsuarioResponseDto response = usuarioService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
