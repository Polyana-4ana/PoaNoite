package polyana.example.poanoite.dto;

public class CasaDeFestaResponseDTO {

    private Long id;
    private String nome;
    private String tipoEvento;
    private String endereco;
    private Integer capacidade;
    private Double precoMedio;
    private Boolean estacionamento;
    private Boolean buffetIncluso;
    private Boolean status;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
