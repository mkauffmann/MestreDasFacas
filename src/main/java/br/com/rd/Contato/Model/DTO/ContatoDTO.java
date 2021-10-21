package br.com.rd.Contato.Model.DTO;

import lombok.Data;

@Data
public class ContatoDTO {
    private Long id;
    private String nome;
    private String email;
    private String titulo;
    private String mensagem;
}
