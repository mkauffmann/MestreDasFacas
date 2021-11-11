package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerCreditCardDTO {
    private Long customerId;
    private String customerName;
    private List<CreditCardDTO> creditCards;

}
