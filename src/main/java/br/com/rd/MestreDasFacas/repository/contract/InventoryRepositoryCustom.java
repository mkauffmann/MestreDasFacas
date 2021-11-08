package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepositoryCustom {

    @Query(value = "update estoque set quantidade_estoque = quantidade_estoque - 1 where id_produto = :id", nativeQuery = true)
    Inventory myInventoryUpdate(@Param("id") Long id);

}
