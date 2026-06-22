package br.com.alura.adopet.api.repository;


import br.com.alura.adopet.api.model.Anexo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface AnexoRepository extends JpaRepository<Anexo, UUID> {
    List<Anexo> findByOcorrenciaId(UUID ocorrenciaId);

    List<Anexo> findByAnimalId(UUID animalId);

    List<Anexo> findByAdocaoId(UUID adocaoId);
}
