package br.com.alura.adopet.api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.alura.adopet.api.dto.animal.AnimalResponseDto;
import br.com.alura.adopet.api.dto.animal.CreateAnimalRequestDto;
import br.com.alura.adopet.api.dto.animal.UpdateAnimalRequestDto;
import br.com.alura.adopet.api.service.AnimalService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalResponseDto> criar(@Valid @RequestBody CreateAnimalRequestDto request) {
        AnimalResponseDto response = animalService.criar(request);

        return ResponseEntity
                .created(URI.create("/api/animais/" + response.id()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> buscarPorId(@PathVariable UUID id) {
        AnimalResponseDto response = animalService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponseDto>> listar() {
        List<AnimalResponseDto> response = animalService.listar();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalResponseDto> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateAnimalRequestDto request
    ) {
        AnimalResponseDto response = animalService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        animalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
