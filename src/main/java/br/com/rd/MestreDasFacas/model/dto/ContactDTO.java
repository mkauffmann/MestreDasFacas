package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class ContactDTO {
    private Long id;
    private String cpf;
    private String complete_name;
    private String email;
    private Long tel;
    private String  motivo;
    private Long pd_numero;
    private String msg;
}
