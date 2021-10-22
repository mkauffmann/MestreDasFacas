package br.com.rd.HPProjetoServico.repository.contract;


import br.com.rd.HPProjetoServico.model.enity.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPagamentoRepository extends JpaRepository <TipoPagamento, Long> {
}
