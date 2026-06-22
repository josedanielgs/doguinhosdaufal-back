package br.com.alura.adopet.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "anexos")
public class Anexo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String nomeOriginal;

    @Column(nullable = false, length = 255)
    private String nomeArmazenado;

    @Column(nullable = false, length = 150)
    private String contentType;

    @Column(nullable = false)
    private Long tamanho;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] dados;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ocorrencia_id", nullable = true)
    private Ocorrencia ocorrencia;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "adocao_id", nullable = true)
    private Adocao adocao;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "animal_id", nullable = true)
    private Animal animal;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    public UUID getId() { return id; }
    public String getNomeOriginal() { return nomeOriginal; }
    public void setNomeOriginal(String nomeOriginal) { this.nomeOriginal = nomeOriginal; }
    public String getNomeArmazenado() { return nomeArmazenado; }
    public void setNomeArmazenado(String nomeArmazenado) { this.nomeArmazenado = nomeArmazenado; }
    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }
    public Long getTamanho() { return tamanho; }
    public void setTamanho(Long tamanho) { this.tamanho = tamanho; }
    public byte[] getDados() { return dados; }
    public void setDados(byte[] dados) { this.dados = dados; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public Ocorrencia getOcorrencia() { return ocorrencia; }
    public void setOcorrencia(Ocorrencia ocorrencia) { this.ocorrencia = ocorrencia; }
    public Animal getAnimal() { return animal; }
    public void setAnimal(Animal animal) { this.animal = animal; }
    public Adocao getAdocao() { return adocao; }
    public void setAdocao(Adocao adocao) { this.adocao = adocao; }

    
}
