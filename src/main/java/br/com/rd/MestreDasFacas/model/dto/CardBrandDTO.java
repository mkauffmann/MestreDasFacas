package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class CardBrandDTO {
    private Long id;
    private String cardBrandName;

}
