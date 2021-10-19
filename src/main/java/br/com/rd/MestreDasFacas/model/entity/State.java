package br.com.rd.MestreDasFacas.model.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "UF")
public class State {
    @Id
    @Column(name = "ID_UF")
    private String uf;

    @Column(name = "NOME_UF")
    private String stateName;

}
