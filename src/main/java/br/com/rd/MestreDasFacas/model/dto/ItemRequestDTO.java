package br.com.rd.MestreDasFacas.model.dto;


import br.com.rd.MestreDasFacas.model.entity.Product;
import lombok.Data;

@Data
public class ItemRequestDTO {

    private Long id;
    private Long quantity;
    private Double total_value;
    private ProductDTO product;



}
