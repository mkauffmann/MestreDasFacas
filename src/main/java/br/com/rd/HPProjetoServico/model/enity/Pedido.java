package br.com.rd.HPProjetoServico.model.enity;

import br.com.rd.HPProjetoServico.model.dto.StatusPedidoDTO;
import br.com.rd.HPProjetoServico.model.dto.TipoPagamentoDTO;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

//@Data
//@Entity(name = "pedido")
//public class Pedido {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id_numero_pedido;
//    @Column(nullable = false)
//    private Double frete_fixo;
//    @Column(nullable = false)
//    private Date data_compra;
//    @Column(nullable = false)
//    private Date data_pagamento;
//    @Column(nullable = false)
//
//    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
//    @JoinColumn(name = "statusPedido")
//    private StatusPedidoDTO PedidoStatus;
//
//}
//
