package br.com.rd.HPProjetoServico.repository.contract;

import br.com.rd.HPProjetoServico.model.enity.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Long> {
}
