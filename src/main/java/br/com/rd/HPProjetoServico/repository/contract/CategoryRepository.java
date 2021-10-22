package br.com.rd.HPProjetoServico.repository.contract;

import br.com.rd.HPProjetoServico.model.enity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Long> {
}
