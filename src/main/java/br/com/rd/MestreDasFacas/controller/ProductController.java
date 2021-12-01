package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.dto.ProductDTO;
//import br.com.rd.MestreDasFacas.model.dto.ProductDTO2;
import br.com.rd.MestreDasFacas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductDTO dto) {
        return this.productService.addProduct(dto);
    }

    @GetMapping
    public List<ProductDTO> showList() {
        return this.productService.showList();
    }

    @GetMapping("/{id}")
    public ProductDTO find(@PathVariable("id") Long id) {
        return this.productService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@RequestBody ProductDTO dto, @PathVariable("id") Long id) {
        return this.productService.update(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.productService.deleteById(id);
    }

    // Barra de busca:

    @GetMapping("/search/{busca}")
    public List<ProductDTO> searchProducts(@PathVariable("busca") String search) {
        return this.productService.listProductsSearch(search);
    }

    // Buscar por categoria:

    @GetMapping("/category/{id}")
    public List<ProductDTO> searchByCategory(@PathVariable("id") Long id) {
        return this.productService.listProductByCategory(id);
    }

    // Ordenar em Decrescente Categoria:

    @GetMapping("/orderDescCatg/{id}")
    public List<ProductDTO> listDescCatg(@PathVariable("id") Long id) {
        return this.productService.listProductsByPriceDescCatg(id);
    }

    // Ordenar em Crescente Categoria:

    @GetMapping("/orderAscCatg/{id}")
    public List<ProductDTO> listAscCatg(@PathVariable("id") Long id) {
        return this.productService.listProductsByPriceAscCatg(id);
    }

    // Ordenar em Decrescente Pesquisa:

    @GetMapping("/orderDescSearch/{search}")
    public List<ProductDTO> listDescSearch(@PathVariable("search") String search) {
        return this.productService.listProductsByPriceDescSearch(search);
    }

    // Ordenar em Crescente Pesquisa:

    @GetMapping("/orderAscSearch/{search}")
    public List<ProductDTO> listAscSearch(@PathVariable("search") String search) {
        return this.productService.listProductsByPriceAscSearch(search);
    }

    // Ordenar em Decrescente:

    @GetMapping("/orderDesc")
    public List<ProductDTO> listDesc() {
        return this.productService.listProductsByPriceDesc();
    }

    // Ordenar em Crescente:

    @GetMapping("/orderAsc")
    public List<ProductDTO> listAsc() {
        return this.productService.listProductsByPriceAsc();
    }

    // Listar Novidades:

    @GetMapping("/news")
    public List<ProductDTO> listNews() {
        return this.productService.listProductsByNews();
    }

    // Listar Destaques:

    @GetMapping("/featured")
    public List<ProductDTO> listFeatured() {
        return this.productService.listProductsByFeatured();
    }

}
