package br.com.rd.MestreDasFacas.model.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TELEFONE")
public class Telephone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TELEFONE")
    private Long id;

    @Column(name = "DDD", nullable = false)
    private String ddd;

    @Column(name = "NUMERO_TELEFONE", nullable = false)
    private String phoneNumber;

}
