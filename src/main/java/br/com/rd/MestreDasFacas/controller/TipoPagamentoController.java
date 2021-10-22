package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.dto.TipoPagamentoDTO;
import br.com.rd.MestreDasFacas.service.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipoPagamento")
public class TipoPagamentoController {
    @Autowired
    TipoPagamentoService tipoPagamentoService;
    @PostMapping
    public TipoPagamentoDTO create(@RequestBody TipoPagamentoDTO tipoPagamento){
        return tipoPagamentoService.createTipoPagamento(tipoPagamento);
    }

    @GetMapping
    public List<TipoPagamentoDTO> findAll(){
        return tipoPagamentoService.findAllMedia();
    }

    @PutMapping("/{id}")
    public TipoPagamentoDTO updateById(@RequestBody TipoPagamentoDTO dto, @PathVariable Long id){
        return tipoPagamentoService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        tipoPagamentoService.deleteById(id);
    }

    @GetMapping("/{id}")
    public TipoPagamentoDTO searchById(@PathVariable("id") Long id) {
        return tipoPagamentoService.searchTipoPagamentoById(id);
    }
}
