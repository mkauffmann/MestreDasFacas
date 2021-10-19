package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.CorCaboDTO;
import br.com.rd.MestreDasFacas.service.CorCaboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corCabo")
public class CorCaboController {

    @Autowired
    CorCaboService corCaboService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CorCaboDTO create(@RequestBody CorCaboDTO dto) {
        return this.corCaboService.addCorCabo(dto);
    }

    @GetMapping
    public List<CorCaboDTO> showList() {
        return this.corCaboService.showListCorCabo();
    }

    @GetMapping("/{id}")
    public CorCaboDTO find(@PathVariable("id") Long id) {
        return this.corCaboService.findCorCaboById(id);
    }

    @PutMapping("/{id}")
    public CorCaboDTO update(@RequestBody CorCaboDTO dto, @PathVariable("id") Long id) {
        return this.corCaboService.updateById(dto, id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        this.corCaboService.deleteById(id);
    }

}
