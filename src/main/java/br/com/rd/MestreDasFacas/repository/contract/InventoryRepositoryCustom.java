package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface InventoryRepositoryCustom {


    // Query de seleção do estoque por id de produto:

    @Query(value = "select * from estoque where id_produto = :id ", nativeQuery = true)
    Optional<Inventory> myFindById(@Param("id") Long id);

}
