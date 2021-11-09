package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface InventoryRepositoryCustom {

    @Transactional
    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value = "update estoque set quantidade_estoque = quantidade_estoque - 1 where id_produto = :id", nativeQuery = true)
    void myInventoryUpdate(@Param("id") Long id);

//    List<Inventory> myInventoryUpdate(Long id);

}
