package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ESTOQUE")
public class Inventory {

    @Id
    @Column(name = "ID_PRODUTO")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ID_PRODUTO")
    private Product product;

    @Column(nullable = false, name = "QUANTIDADE_ESTOQUE")
    private Integer quantityInventory;

}
