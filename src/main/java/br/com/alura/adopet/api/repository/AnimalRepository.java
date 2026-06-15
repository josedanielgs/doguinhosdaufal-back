package br.com.alura.adopet.api.repository;

import br.com.alura.adopet.api.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnimalRepository extends JpaRepository<Animal, UUID> {
}