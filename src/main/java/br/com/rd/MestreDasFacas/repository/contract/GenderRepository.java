package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> findByDescription(String desc);
}
