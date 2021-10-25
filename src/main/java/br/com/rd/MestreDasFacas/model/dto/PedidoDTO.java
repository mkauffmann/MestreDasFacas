package br.com.rd.MestreDasFacas.model.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class PedidoDTO {
    private Long id_numero_pedido;
    private Double frete_fixo;
    private Date data_compra;
    private Date data_pagamento;
    private StatusRequestDTO statusPedido;
    private TypePaymentDTO typePayment;

}
