package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.BrandDTO;
import br.com.rd.MestreDasFacas.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public BrandDTO create(@RequestBody BrandDTO dto) {
        return this.brandService.addBrand(dto);
    }

    @GetMapping
    public List<BrandDTO> showList() {
        return this.brandService.showListBrand();
    }

    @GetMapping("/{id}")
    public BrandDTO find(@PathVariable("id") Long id) {
        return this.brandService.findBrandById(id);
    }

    @PutMapping("/{id}")
    public BrandDTO update(@RequestBody BrandDTO dto, @PathVariable("id") Long id) {
        return this.brandService.updateById(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.brandService.deleteById(id);
    }







}
