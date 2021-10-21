package br.com.rd.MestreDasFacas.model.dto;

import br.com.rd.MestreDasFacas.model.entity.Brand;
import br.com.rd.MestreDasFacas.model.entity.CableColor;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String productName;
    private String descriptionProduct;
    private Double weight;
    private Double length;
    private Double height;
    private Double width;
    private BrandDTO brand;
    private CableColorDTO cableColor;

}
