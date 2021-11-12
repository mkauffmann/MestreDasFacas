package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class PasswordDTO {
    private Long customerId;
    private String currentPassword;
    private String password;
}
