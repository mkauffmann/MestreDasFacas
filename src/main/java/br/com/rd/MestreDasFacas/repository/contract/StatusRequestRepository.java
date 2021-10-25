package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.enity.StatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRequestRepository extends JpaRepository<StatusRequest, Long> {
}
