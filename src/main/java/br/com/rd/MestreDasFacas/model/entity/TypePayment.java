package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TIPO_PAGAMENTO")
public class TypePayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PAGAMENTO")
    private Long id;

    @Column(nullable = false, name = "DESCRICAO_TIPO_PAGAMENTO")
    private String description_type_payment;

}
