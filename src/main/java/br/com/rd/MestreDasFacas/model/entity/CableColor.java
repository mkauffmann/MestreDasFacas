package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "corDoCabo")
public class CableColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "corDoCabo")
    private String cableColor;

}
