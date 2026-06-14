package br.com.alura.adopet.api.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "voluntarios")
public class Voluntario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String nome;

    @Size(max = 500)
    @Column(name = "foto_url", length = 500)
    private String fotoUrl;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Size(max = 20)
    @Column(length = 20)
    private String telefone;

    @Email
    @Size(max = 150)
    @Column(length = 150)
    private String email;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public Voluntario() {
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
