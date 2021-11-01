package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositoryCustom {

    @Query(value = "Select * from produto p where p.nome_produto LIKE %:search%", nativeQuery = true)
    List<Product> myFindAllBySearch(@Param("search") String search);

    @Query(value = "Select * from produto p where p.id_categoria = :idcategoria", nativeQuery = true)
    List<Product> myFindAllByIdCategory(@Param("idcategoria") Long id);

}
