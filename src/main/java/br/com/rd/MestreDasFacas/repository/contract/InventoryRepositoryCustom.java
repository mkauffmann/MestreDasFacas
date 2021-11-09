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

//    @Transactional
//    @Modifying(clearAutomatically=true, flushAutomatically=true)
//    @Query(value = "update estoque set quantidade_estoque = quantidade_estoque - 1 where id_produto = :id", nativeQuery = true)
//    void myInventoryUpdate(@Param("id") Long id);

//    List<Inventory> myInventoryUpdate(Long id);

    @Query(value = "select * from estoque where id_produto = :id ", nativeQuery = true)
    Optional<Inventory> myFindById(@Param("id") Long id);

}
