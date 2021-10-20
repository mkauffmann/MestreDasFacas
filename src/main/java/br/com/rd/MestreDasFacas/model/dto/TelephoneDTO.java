package br.com.rd.MestreDasFacas.model.dto;


import lombok.Data;

import javax.persistence.*;

@Data
public class TelephoneDTO {
    private Long id;
    private String ddd;
    private String phoneNumber;

}
