package br.com.rd.MestreDasFacas.repository.contract;


import org.springframework.data.jpa.repository.JpaRepository;
import br.com.rd.MestreDasFacas.model.entity.Contato;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}

