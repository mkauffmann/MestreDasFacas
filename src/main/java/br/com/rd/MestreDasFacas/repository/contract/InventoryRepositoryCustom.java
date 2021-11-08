package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepositoryCustom {

    @Modifying
    @Query(value = "update estoque etq set etq.quantidade_estoque = quantidade_estoque - 1 where etq.id_produto = :id", nativeQuery = true)
    void myInventoryUpdate(@Param("id") Long id);

//    List<Inventory> myInventoryUpdate(Long id);

}
