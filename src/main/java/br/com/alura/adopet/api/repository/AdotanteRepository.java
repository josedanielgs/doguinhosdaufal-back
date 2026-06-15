package br.com.alura.adopet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.adopet.api.model.Adotante;

import java.util.UUID;

public interface AdotanteRepository extends JpaRepository<Adotante, UUID> {
}
