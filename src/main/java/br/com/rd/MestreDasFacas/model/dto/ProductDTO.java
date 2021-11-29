package br.com.rd.MestreDasFacas.model.dto;

import br.com.rd.MestreDasFacas.model.entity.Brand;
//import br.com.rd.MestreDasFacas.model.entity.CableColor;
import br.com.rd.MestreDasFacas.model.entity.ProductPrice;
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
    private CategoryDTO category;
    private ProductPriceDTO productPrice;
    private String image;
    private Boolean news;
    private Boolean featured;

}
