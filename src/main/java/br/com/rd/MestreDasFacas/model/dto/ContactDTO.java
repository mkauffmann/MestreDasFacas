package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class ContactDTO {
    private Long id;
    private String nome;
    private String email;
    private String titulo;
    private String mensagem;
}
