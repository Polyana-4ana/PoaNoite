package polyana.example.poanoite.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class LocalEventoCreateDTO {

    private Long id;

    @NotBlank(message = "O nome da casa de festa é obrigatório")
    private String nome;

    @NotBlank(message = "O tipo de evento é obrigatório")
    private String tipoEvento;

    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;

    @NotNull(message = "A capacidade é obrigatória")
    @Positive(message = "A capacidade deve ser maior que 0")
    private Integer capacidade;

    @NotNull(message = "O preço médio é obrigatório")
    @Positive(message = "O preço deve ser maior que 0")
    private Double precoMedio;

    @NotNull(message = "O status é obrigatório")
    private Boolean status;

    @NotNull(message = "Informar se possui estacionamento é obrigatório")
    private Boolean estacionamento;

    @NotNull(message = "Informar se possui buffet incluso é obrigatório")
    private Boolean buffetIncluso;

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Double getPrecoMedio() {
        return precoMedio;
    }

    public void setPrecoMedio(Double precoMedio) {
        this.precoMedio = precoMedio;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getEstacionamento() {
        return estacionamento;
    }

    public void setEstacionamento(Boolean estacionamento) {
        this.estacionamento = estacionamento;
    }

    public Boolean getBuffetIncluso() {
        return buffetIncluso;
    }

    public void setBuffetIncluso(Boolean buffetIncluso) {
        this.buffetIncluso = buffetIncluso;
    }
}
