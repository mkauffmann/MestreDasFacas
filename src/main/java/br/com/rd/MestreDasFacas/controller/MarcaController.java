package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.CorCaboDTO;
import br.com.rd.MestreDasFacas.model.dto.MarcaDTO;
import br.com.rd.MestreDasFacas.model.entity.CorCabo;
import br.com.rd.MestreDasFacas.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public MarcaDTO create(@RequestBody MarcaDTO dto) {
        return this.marcaService.addMarca(dto);
    }

    @GetMapping
    public List<MarcaDTO> showList() {
        return this.marcaService.showListMarca();
    }

    @GetMapping("/{id}")
    public MarcaDTO find(@PathVariable("id") Long id) {
        return this.marcaService.findMarcaById(id);
    }

    @PutMapping("/{id}")
    public MarcaDTO update(@RequestBody MarcaDTO dto, @PathVariable("id") Long id) {
        return this.marcaService.updateById(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.marcaService.deleteById(id);
    }







}
