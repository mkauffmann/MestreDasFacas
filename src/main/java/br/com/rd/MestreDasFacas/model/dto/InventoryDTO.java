package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class InventoryDTO {

    private Long id;
    private ProductDTO product;
    private Integer quantityInventory;
}
