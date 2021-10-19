package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "marca")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "marca")
    private String brand;

}
