package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
