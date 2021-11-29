package br.com.rd.MestreDasFacas.model.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "PRODUTO")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(nullable = false, name = "NOME_PRODUTO")
    private String productName;

    @Column(nullable = false, name = "DESCRICAO_PRODUTO")
    private String descriptionProduct;

    @Column(nullable = false, name = "PESO")
    private Double weight;

    @Column(nullable = false, name = "COMPRIMENTO")
    private Double length;

    @Column(nullable = false, name = "ALTURA")
    private Double height;

    @Column(nullable = false, name = "LARGURA")
    private Double width;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_MARCA")
    private Brand brand;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CATEGORIA")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_PRECO_PRODUTO")
    private ProductPrice productPrice;


    @Column(columnDefinition = "TEXT", name = "IMAGEM")
    private String image;

    @Column(nullable = false, name = "NOVIDADE")
    private Boolean news;

    @Column(nullable = false, name = "DESTAQUE")
    private Boolean featured;

}
