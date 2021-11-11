package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "STATUS_PEDIDO")
public class DeliveryStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_STATUS_PEDIDO")
    private Long id;

    @Column(nullable = false, name = "DESCRICAO_STATUS_PEDIDO")
    private String description_status_delivery;

}