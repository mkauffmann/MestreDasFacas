package br.com.rd.MestreDasFacas.repository.contract;


import br.com.rd.MestreDasFacas.model.enity.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPagamentoRepository extends JpaRepository <TipoPagamento, Long> {
}
