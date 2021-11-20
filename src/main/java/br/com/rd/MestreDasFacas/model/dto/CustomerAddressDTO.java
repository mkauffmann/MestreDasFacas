package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerAddressDTO {
    private Long customerId;
    private String customerName;
    private List<AddressDTO> addresses;
}
