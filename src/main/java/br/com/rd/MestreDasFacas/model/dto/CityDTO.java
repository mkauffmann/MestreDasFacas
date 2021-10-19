package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

import javax.persistence.*;

@Data
public class CityDTO {
    private Integer id;
    private String cityName;

}
