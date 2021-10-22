package br.com.rd.HPProjetoServico.model.enity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "tipo_pagamento")
public class TipoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_pagamento;
    //    @NotNull(message = "Brand field is empty")
    @Column(nullable = false)
    private String descricao_tipo_pagamento;
    //    @NotNull(message = "Model field is empty")

}
