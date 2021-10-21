package br.com.rd.Contato.Model.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name= "contato")
@Data
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String titulo;
    @Column(nullable = false)
    private String mensagem;

}