package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long id;
    private String street;
    private Integer number;
    private String complement;
    private String cep;
    private String neighborhood;
    private CityDTO city;
    private StateDTO state;
}
