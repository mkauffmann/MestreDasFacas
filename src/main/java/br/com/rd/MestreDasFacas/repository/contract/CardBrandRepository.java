package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.CardBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardBrandRepository extends JpaRepository<CardBrand, Long> {
    Optional<CardBrand> findByCardBrandName(String brandName);
}
