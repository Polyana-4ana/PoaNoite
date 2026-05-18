package polyana.example.poanoite.repository;

import polyana.example.poanoite.domain.LocalEvento;
import org.springframework.data.jpa.domain.Specification;

public class CasaDeFestaSpecification {

    public static Specification<LocalEvento> temNome(String nome) {
        return (root, query, cb) ->
                nome == null ? null : cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<LocalEvento> temTipoEvento(String tipoEvento) {
        return (root, query, cb) ->
                tipoEvento == null ? null : cb.equal(root.get("tipoEvento"), tipoEvento);
    }

    public static Specification<LocalEvento> temCidade(String cidade) {
        return (root, query, cb) ->
                cidade == null ? null : cb.equal(root.get("cidade"), cidade);
    }

    public static Specification<LocalEvento> temCapacidade(Integer capacidade) {
        return (root, query, cb) ->
                capacidade == null ? null : cb.greaterThanOrEqualTo(root.get("capacidade"), capacidade);
    }

    public static Specification<LocalEvento> temPrecoMaximo(Double precoMaximo) {
        return (root, query, cb) ->
                precoMaximo == null ? null : cb.lessThanOrEqualTo(root.get("precoMedio"), precoMaximo);
    }

    public static Specification<LocalEvento> temBuffetIncluso(Boolean buffetIncluso) {
        return (root, query, cb) ->
                buffetIncluso == null ? null : cb.equal(root.get("buffetIncluso"), buffetIncluso);
    }

    public static Specification<LocalEvento> temEstacionamento(Boolean estacionamento) {
        return (root, query, cb) ->
                estacionamento == null ? null : cb.equal(root.get("estacionamento"), estacionamento);
    }

    public static Specification<LocalEvento> temStatus(Boolean status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("status"), status);
    }
}
