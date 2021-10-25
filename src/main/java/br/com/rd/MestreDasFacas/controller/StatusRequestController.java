package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.dto.StatusRequestDTO;
import br.com.rd.MestreDasFacas.service.StatusRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statusPedido")
public class StatusRequestController {
    @Autowired
    StatusRequestService statusPedidoService;
    @PostMapping
    public StatusRequestDTO create(@RequestBody StatusRequestDTO statusPedido){
        return statusPedidoService.createStatusPedido(statusPedido);
    }

    @GetMapping
    public List<StatusRequestDTO> findAll(){
        return statusPedidoService.findAllMedia();
    }

    @PutMapping("/{id}")
    public StatusRequestDTO updateById(@RequestBody StatusRequestDTO dto, @PathVariable Long id){
        return statusPedidoService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        statusPedidoService.deleteById(id);
    }

    @GetMapping("/{id}")
    public StatusRequestDTO searchById(@PathVariable("id") Long id) {
        return statusPedidoService.searchStatusPedidoById(id);
    }
}
