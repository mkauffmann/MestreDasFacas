package br.com.rd.MestreDasFacas.model.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private Date birthDate;
    private String password;
    private GenderDTO gender;
    private List<TelephoneDTO> telephones;
    private List<AddressDTO> addresses;
    private List<CreditCardDTO> creditCards;

}
