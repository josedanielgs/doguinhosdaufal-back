package br.com.alura.adopet.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.adopet.api.model.Ocorrencia;
import br.com.alura.adopet.api.model.Voluntario;

import java.util.List;
import java.util.UUID;

public interface VoluntarioRepository extends JpaRepository<Voluntario, UUID> {

}
