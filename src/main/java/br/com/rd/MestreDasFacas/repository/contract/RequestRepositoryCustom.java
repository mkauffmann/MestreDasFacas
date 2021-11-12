package br.com.rd.MestreDasFacas.repository.contract;

import br.com.rd.MestreDasFacas.model.entity.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RequestRepositoryCustom {

    @Query(value = "Select * from pedido p where p.id_cliente = :id", nativeQuery = true)
    List<Request> myFindAllRequestByCustomer(@Param("id") Long id);
}
