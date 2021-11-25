package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name= "contato")
@Data
public class Contact {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String complete_name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private Long tel;
    @Column(nullable = false)
    private String  motivo;
    @Column(nullable = false)
    private Long pd_numero;
    @Column(nullable = false)
    private String msg;




}