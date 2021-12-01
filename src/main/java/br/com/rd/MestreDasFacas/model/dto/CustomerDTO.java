package br.com.rd.MestreDasFacas.model.dto;


import br.com.rd.MestreDasFacas.model.entity.Address;
import br.com.rd.MestreDasFacas.model.entity.CreditCard;
import br.com.rd.MestreDasFacas.model.entity.Gender;
import br.com.rd.MestreDasFacas.model.entity.Telephone;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthDate;
    private String password;
    private GenderDTO gender;
    private List<TelephoneDTO> telephones;
    private List<AddressDTO> addresses;
    private List<CreditCardDTO> creditCards;
    private String resetPasswordToken;

}
