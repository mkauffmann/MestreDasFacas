package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class ContatoDTO {
    private Long id;
    private String nome;
    private String email;
    private String titulo;
    private String mensagem;
}
