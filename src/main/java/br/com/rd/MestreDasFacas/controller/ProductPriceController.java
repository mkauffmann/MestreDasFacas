package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.ProductPriceDTO;
import br.com.rd.MestreDasFacas.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productPrice")
public class ProductPriceController {

    @Autowired
    ProductPriceService productPriceService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ProductPriceDTO create(@RequestBody ProductPriceDTO dto) {
        return this.productPriceService.addProductPrice(dto);
    }

    @GetMapping
    public List<ProductPriceDTO> showList() {
        return this.productPriceService.showListProductPrice();
    }

    @PutMapping("/{id}")
    public ProductPriceDTO update(@RequestBody ProductPriceDTO dto, Long id) {
        return this.productPriceService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.productPriceService.deleteById(id);
    }

}
