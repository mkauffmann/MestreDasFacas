package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

@Data
public class ProductDTO2 {

    private String productName;
    private String descriptionProduct;
    private Double weight;
    private Double length;
    private Double height;
    private Double width;
    private BrandDTO2 brand;
    private CableColorDTO2 cableColor;
    private CategoryDTO2 category;

}
