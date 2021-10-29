package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class GenderDTO {
    private Long id;
    private String description;
}
