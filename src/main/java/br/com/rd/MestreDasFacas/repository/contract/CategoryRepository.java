package br.com.rd.MestreDasFacas.repository.contract;


import br.com.rd.MestreDasFacas.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
