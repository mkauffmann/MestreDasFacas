package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "PRECO_PRODUTO")
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRECO_PRODUTO")
    private Long id;

    @Column(nullable = false, name = "VALOR_PRECO")
    private Double value;

}
