package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerTelephoneDTO {
    private Long customerId;
    private String customerName;
    private List<TelephoneDTO> telephones;

}
