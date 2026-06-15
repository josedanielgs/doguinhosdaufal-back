package br.com.alura.adopet.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.adopet.api.model.Ocorrencia;

import java.util.List;
import java.util.UUID;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, UUID> {
    List<Ocorrencia> findByAnimalId(UUID animalId);
}
