package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "CATEGORIA")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Long id;

    @Column(nullable = false, name = "DESCRICAO_CATEGORIA")
    private String description_category;


}
