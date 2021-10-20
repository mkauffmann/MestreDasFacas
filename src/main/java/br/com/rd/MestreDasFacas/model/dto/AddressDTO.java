package br.com.rd.MestreDasFacas.model.dto;

import br.com.rd.MestreDasFacas.model.entity.City;
import br.com.rd.MestreDasFacas.model.entity.State;
import lombok.Data;

import javax.persistence.*;

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
