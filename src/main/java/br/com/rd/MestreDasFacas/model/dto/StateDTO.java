package br.com.rd.MestreDasFacas.model.dto;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
public class StateDTO {
    private String uf;
    private String stateName;

}
