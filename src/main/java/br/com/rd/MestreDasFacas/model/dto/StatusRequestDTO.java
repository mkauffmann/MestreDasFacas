package br.com.rd.MestreDasFacas.model.dto;


import lombok.Data;

@Data
public class StatusRequestDTO {
    private Long id_status_pedido;
    private String description_status_pedido;

}