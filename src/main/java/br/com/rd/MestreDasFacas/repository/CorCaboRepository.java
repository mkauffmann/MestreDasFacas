package br.com.rd.MestreDasFacas.repository;

import br.com.rd.MestreDasFacas.model.entity.CorCabo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorCaboRepository extends JpaRepository<CorCabo, Long> {
}
