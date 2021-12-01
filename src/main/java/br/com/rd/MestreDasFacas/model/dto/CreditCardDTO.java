package br.com.rd.MestreDasFacas.model.dto;

import br.com.rd.MestreDasFacas.model.entity.CardBrand;
import lombok.Data;

import javax.persistence.*;

@Data
public class CreditCardDTO {
    private Long id;
    private String cardNumber;
    private String lastFourDigits;
    private String cardValidDate;
    private String cvv;
    private String cpf;
    private String holderName;
    private CardBrandDTO cardBrand;
}
