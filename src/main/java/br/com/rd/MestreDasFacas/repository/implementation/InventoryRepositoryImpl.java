//package br.com.rd.MestreDasFacas.repository.implementation;
//
//import br.com.rd.MestreDasFacas.model.entity.Inventory;
//import br.com.rd.MestreDasFacas.repository.contract.InventoryRepositoryCustom;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.util.List;
//
//public class InventoryRepositoryImpl implements InventoryRepositoryCustom {
//
//    @PersistenceContext
//    EntityManager entityManager;
//
//    @Override
//    public List<Inventory> myInventoryUpdate(Long id) {
//        Query sql = this.entityManager.createNativeQuery(
//            "update estoque set quantidade_estoque = quantidade_estoque - 1 where id_produto = :id", Inventory.class
//        );
//        sql.setParameter("id", id);
//
//        List<Inventory> listInventory = sql.getResultList();
//        return listInventory;
//    }
//}
