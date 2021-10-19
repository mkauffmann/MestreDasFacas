package br.com.rd.MestreDasFacas.repository;

import br.com.rd.MestreDasFacas.model.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
