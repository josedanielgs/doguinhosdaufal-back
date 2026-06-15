package br.com.alura.adopet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.adopet.api.model.Procedimento;

import java.util.List;
import java.util.UUID;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, UUID> {
    List<Procedimento> findByOcorrenciaId(UUID ocorrenciaId);
}