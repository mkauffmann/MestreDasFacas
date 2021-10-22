package br.com.rd.HPProjetoServico.controller;


import br.com.rd.HPProjetoServico.model.dto.StatusPedidoDTO;
import br.com.rd.HPProjetoServico.service.StatusPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statusPedido")
public class StatusPedidoController {
    @Autowired
    StatusPedidoService statusPedidoService;
    @PostMapping
    public StatusPedidoDTO create(@RequestBody StatusPedidoDTO statusPedido){
        return statusPedidoService.createStatusPedido(statusPedido);
    }

    @GetMapping
    public List<StatusPedidoDTO> findAll(){
        return statusPedidoService.findAllMedia();
    }

    @PutMapping("/{id}")
    public StatusPedidoDTO updateById(@RequestBody StatusPedidoDTO dto, @PathVariable Long id){
        return statusPedidoService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        statusPedidoService.deleteById(id);
    }

    @GetMapping("/{id}")
    public StatusPedidoDTO searchById(@PathVariable("id") Long id) {
        return statusPedidoService.searchStatusPedidoById(id);
    }
}
