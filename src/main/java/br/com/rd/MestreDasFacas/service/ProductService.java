package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.*;

import br.com.rd.MestreDasFacas.model.entity.Category;
import br.com.rd.MestreDasFacas.model.entity.Brand;
//import br.com.rd.MestreDasFacas.model.entity.CableColor;
import br.com.rd.MestreDasFacas.model.entity.Product;
import br.com.rd.MestreDasFacas.model.entity.ProductPrice;
import br.com.rd.MestreDasFacas.repository.contract.BrandRepository;
//import br.com.rd.MestreDasFacas.repository.contract.CableColorRepository;
import br.com.rd.MestreDasFacas.repository.contract.ProductRepository;

import br.com.rd.MestreDasFacas.repository.contract.CategoryRepository;
import br.com.rd.MestreDasFacas.repository.contract.ProductPriceRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;


    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductPriceRepository productPriceRepository;

    @Autowired
    DtoConversion dtoConversion;


    // Métodos de adição:

    public ProductDTO addProduct(ProductDTO dto) {
        Product newProduct = dtoConversion.productDtoToBusiness(dto);
        newProduct = productRepository.save(newProduct);
        return dtoConversion.productBusinessToDto(newProduct);
    }

    public List<ProductDTO> showList() {
        List<Product> list = productRepository.findAll();
        return dtoConversion.productListToDto(list);
    }

    public ProductDTO findById(Long id) {
        Optional<Product> op = productRepository.findById(id);

        if(op.isPresent()) {
            return dtoConversion.productBusinessToDto(op.get());
        }

        return null;
    }

    public ProductDTO update(ProductDTO dto, Long id) {
        Optional<Product> op = productRepository.findById(id);

        if(op.isPresent()) {

            Product obj = op.get();

            if(dto.getProductName() != null) {
                obj.setProductName(dto.getProductName());
            }

            if(dto.getDescriptionProduct() != null) {
                obj.setDescriptionProduct(dto.getDescriptionProduct());
            }

            if(dto.getHeight() != null) {
                obj.setHeight(dto.getHeight());
            }

            if(dto.getWidth() != null) {
                obj.setWidth(dto.getWidth());
            }

            if(dto.getLength() != null) {
                obj.setLength(dto.getLength());
            }

            if(dto.getWeight() != null) {
                obj.setWeight(dto.getWeight());
            }

            if(dto.getBrand() != null) {
                Brand newBrand = dtoConversion.brandDtoToBusiness(dto.getBrand());
                obj.setBrand(newBrand);
            }



            if(dto.getCategory() != null) {
                Category newCategory = dtoConversion.categoryDtoToBusiness(dto.getCategory());
                obj.setCategory(newCategory);
            }

            productRepository.save(obj);
            return dtoConversion.productBusinessToDto(obj);

        }
        return null;
    }

    public void deleteById(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    // Método para barra de busca:

    public List<ProductDTO> listProductsSearch(String search) {
        List<Product> productList = this.productRepository.findByProductNameContaining(search);
        return dtoConversion.productListToDto(productList);
    }

    // Método para listar produtos pelo id de categoria:

    public List<ProductDTO> listProductByCategory(Long id) {
        List<Product> productList = this.productRepository.findByCategoryIdEquals(id);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e categoria decrescente:

    public List<ProductDTO> listProductsByPriceDescCatg(Long id) {
        List<Product> productList = this.productRepository.myFindByPriceDescCatg(id);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e categoria crescente:

    public List<ProductDTO> listProductsByPriceAscCatg(Long id) {
        List<Product> productList = this.productRepository.myFindByPriceAscCatg(id);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e pesquisa decrescente:

    public List<ProductDTO> listProductsByPriceDescSearch(String search) {
        List<Product> productList = this.productRepository.myFindByPriceDescSearch(search);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço e pesquisa crescente:

    public List<ProductDTO> listProductsByPriceAscSearch(String search) {
        List<Product> productList = this.productRepository.myFindByPriceAscSearch(search);
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço decrescente:

    public List<ProductDTO> listProductsByPriceDesc() {
        List<Product> productList = this.productRepository.myFindByPriceDesc();
        return dtoConversion.productListToDto(productList);
    }

    // Método para ordenar produtos pelo preço decrescente:

    public List<ProductDTO> listProductsByPriceAsc() {
        List<Product> productList = this.productRepository.myFindByPriceAsc();
        return dtoConversion.productListToDto(productList);
    }

    // Método para listar produtos destaque:

    public List<ProductDTO> listProductsByFeatured() {
        List<Product> productList = this.productRepository.findByFeaturedTrue();
        return dtoConversion.productListToDto(productList);
    }

    // Método para listar produtos novidade:

    public List<ProductDTO> listProductsByNews() {
        List<Product> productList = this.productRepository.findByNewsTrue();
        return dtoConversion.productListToDto(productList);
    }



}
