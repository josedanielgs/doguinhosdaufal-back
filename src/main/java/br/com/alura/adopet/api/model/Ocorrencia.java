package br.com.alura.adopet.api.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ocorrencias")
public class Ocorrencia {

     @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoOcorrencia tipo;

    @Column(nullable = false)
    private Boolean programada = false;

    @Column(nullable = false)
    private LocalDate data;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;

    @Column(name = "acoes_imediatas", columnDefinition = "TEXT")
    private String acoesImediatas;

    @Column(name = "acoes_resolucao", columnDefinition = "TEXT")
    private String acoesResolucao;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por_id")
    private Usuario criadoPor;

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Procedimento> procedimentos = new ArrayList<>();

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Adocao> adocoes = new ArrayList<>();

    @OneToMany(mappedBy = "ocorrencia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anexo> anexos = new ArrayList<>();

    public Ocorrencia() {
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    public void adicionarProcedimento(Procedimento procedimento) {
        procedimento.setOcorrencia(this);
        this.procedimentos.add(procedimento);
    }

    public void removerProcedimento(Procedimento procedimento) {
        procedimento.setOcorrencia(null);
        this.procedimentos.remove(procedimento);
    }

    public UUID getId() {
        return id;
    }

    public TipoOcorrencia getTipo() {
        return tipo;
    }

    public void setTipo(TipoOcorrencia tipo) {
        this.tipo = tipo;
    }

    public Boolean getProgramada() {
        return programada;
    }

    public void setProgramada(Boolean programada) {
        this.programada = programada;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAcoesImediatas() {
        return acoesImediatas;
    }

    public void setAcoesImediatas(String acoesImediatas) {
        this.acoesImediatas = acoesImediatas;
    }

    public String getAcoesResolucao() {
        return acoesResolucao;
    }

    public void setAcoesResolucao(String acoesResolucao) {
        this.acoesResolucao = acoesResolucao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Usuario getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Usuario criadoPor) {
        this.criadoPor = criadoPor;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<Procedimento> procedimentos) {
        this.procedimentos = procedimentos;
    }

    public List<Anexo> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<Anexo> anexos) {
        this.anexos = anexos;
    }
}
