package br.com.rd.HPProjetoServico.model.dto;


import lombok.Data;

import java.sql.Date;

@Data
public class PedidoDTO {
    private Long id_numero_pedido;
    private Double frete_fixo;
    private Date data_compra;
    private Date data_pagamento;
    private StatusPedidoDTO statusPedido;
    private TipoPagamentoDTO tipoPagamento;

}
