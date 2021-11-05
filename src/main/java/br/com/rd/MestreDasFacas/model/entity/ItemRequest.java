package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity (name = "ITEM_PEDIDO")
@Data
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "QUANTIDADE", nullable = false, unique = true)
    private Long quantity;

    @Column(name = "VALOR_TOTAL", nullable = false, unique = true)
    private Double total_value;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ID_PRODUTO")
    private Product product;


























}
