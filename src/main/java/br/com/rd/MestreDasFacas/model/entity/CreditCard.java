package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "CARTAO_CREDITO")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO_CREDITO")
    private Long id;

    @Column(name = "NUMERO_CARTAO", nullable = false)
    private String cardNumber;

    @Column(name = "CPF_TITULAR", nullable = false)
    private String cpf;

    @Column(name = "NOME_TITULAR", nullable = false)
    private String holderName;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_BANDEIRA")
    private CardBrand cardBrand;
}
