package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.anexos.AnexoResponseAdocaoDto;
import br.com.alura.adopet.api.dto.anexos.AnexoResponseAnimalDto;
import br.com.alura.adopet.api.dto.anexos.AnexoResponseOcorrenciaDto;
import br.com.alura.adopet.api.model.Anexo;
import br.com.alura.adopet.api.service.AnexoService;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/anexos")
public class AnexoController {

    private final AnexoService anexoService;

    public AnexoController(AnexoService anexoService) {
        this.anexoService = anexoService;
    }

    @PostMapping(value = "/ocorrencias/{ocorrenciaId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnexoResponseOcorrenciaDto> uploadParaOcorrencia(
            @PathVariable UUID ocorrenciaId,
            @RequestParam("arquivo") MultipartFile arquivo
    ) throws IOException {
        return ResponseEntity.ok(anexoService.uploadParaOcorrencia(ocorrenciaId, arquivo));
    }

    @GetMapping("/ocorrencias/{ocorrenciaId}")
    public ResponseEntity<List<AnexoResponseOcorrenciaDto>> listarPorOcorrencia(@PathVariable UUID ocorrenciaId) {
        return ResponseEntity.ok(anexoService.listarPorOcorrencia(ocorrenciaId));
    }

    @PostMapping(value = "/animais/{animalId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnexoResponseAnimalDto> uploadParaAnimal(
            @PathVariable UUID animalId,
            @RequestParam("arquivo") MultipartFile arquivo
    ) throws IOException {
        return ResponseEntity.ok(anexoService.uploadParaAnimal(animalId, arquivo));
    }

    @GetMapping("/animais/{animalId}")
    public ResponseEntity<List<AnexoResponseAnimalDto>> listarPorAnimal(@PathVariable UUID animalId) {
        return ResponseEntity.ok(anexoService.listarPorAnimal(animalId));
    }

    @PostMapping(value = "/adocoes/{adocaoId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnexoResponseAdocaoDto> uploadParaAdocao(
            @PathVariable UUID adocaoId,
            @RequestParam("arquivo") MultipartFile arquivo
    ) throws IOException {
        return ResponseEntity.ok(anexoService.uploadParaAdocao(adocaoId, arquivo));
    }

    @GetMapping("/adocoes/{adocaoId}")
    public ResponseEntity<List<AnexoResponseAdocaoDto>> listarPorAdocao(@PathVariable UUID adocaoId) {
        return ResponseEntity.ok(anexoService.listarPorAdocao(adocaoId));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> download(@PathVariable UUID id) {
        Anexo anexo = anexoService.buscarEntidade(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(anexo.getContentType()));
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename(anexo.getNomeOriginal())
                        .build()
        );

        return ResponseEntity.ok()
                .headers(headers)
                .body(anexo.getDados());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        anexoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
