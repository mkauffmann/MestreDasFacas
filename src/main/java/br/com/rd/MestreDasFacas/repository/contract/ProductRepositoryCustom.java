package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositoryCustom {

    @Query(value = "select * from produto p inner join preco_produto pp on p.id_preco_produto = pp.id_preco_produto where id_categoria = :id order by pp.valor_preco desc", nativeQuery = true)
    List<Product> myFindByPriceDescCatg(@Param("id") Long id);

    @Query(value = "select * from produto p inner join preco_produto pp on p.id_preco_produto = pp.id_preco_produto where id_categoria = :id order by pp.valor_preco;", nativeQuery = true)
    List<Product> myFindByPriceAscCatg(@Param("id") Long id);

    @Query(value = "select * from produto p inner join preco_produto pp on p.id_preco_produto = pp.id_preco_produto where nome_produto LIKE %:search% order by pp.valor_preco desc", nativeQuery = true)
    List<Product> myFindByPriceDescSearch(@Param("search") String search);

    @Query(value = "select * from produto p inner join preco_produto pp on p.id_preco_produto = pp.id_preco_produto where nome_produto LIKE %:search% order by pp.valor_preco", nativeQuery = true)
    List<Product> myFindByPriceAscSearch(@Param("search") String search);

    @Query(value = "select * from produto p inner join preco_produto pp on p.id_preco_produto = pp.id_preco_produto order by pp.valor_preco desc", nativeQuery = true)
    List<Product> myFindByPriceDesc();

    @Query(value = "select * from produto p inner join preco_produto pp on p.id_preco_produto = pp.id_preco_produto order by pp.valor_preco;", nativeQuery = true)
    List<Product> myFindByPriceAsc();


}
