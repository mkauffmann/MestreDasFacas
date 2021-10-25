package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class CreditCardDTO {
    private Long id;
    private String cardNumber;
    private String cpf;
    private String holderName;
    private CardBrandDTO cardBrand;
}
