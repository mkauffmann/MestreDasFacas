package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    List<Product> findByProductNameContaining(String search);

    List<Product> findByCategoryIdEquals(Long id);

    List<Product> findByFeaturedTrue();

    List<Product> findByNewsTrue();

}
