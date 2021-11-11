package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {
    @Query(value = "SELECT * FROM TELEFONE WHERE DDD = :ddd AND NUMERO_TELEFONE = :tel", nativeQuery = true)
    Optional<Telephone> findTelephone(@Param("ddd") String ddd, @Param("tel") String tel);
}
