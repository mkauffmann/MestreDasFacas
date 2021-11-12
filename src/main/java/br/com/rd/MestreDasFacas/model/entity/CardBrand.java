package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "BANDEIRA")
public class CardBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BANDEIRA")
    private Long id;

    @Column(name = "DESCRICAO_BANDEIRA", nullable = false, unique = true)
    private String cardBrandName;

}
