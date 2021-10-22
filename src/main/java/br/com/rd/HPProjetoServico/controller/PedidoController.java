package br.com.rd.HPProjetoServico.controller;

import br.com.rd.HPProjetoServico.model.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/Pedido")
//public class PedidoController {
//    @Autowired
//    PedidoService pedidoService;
//    @PostMapping
//    public PedidoDTO create(@RequestBody PedidoDTO pedido){
//        return pedidoService.createPedido(pedido);
//    }
//
//    @GetMapping
//    public List<PedidoDTO> findAll(){
//        return pedidoService.findAllMedia();
//    }
//
//    @PutMapping("/{id}")
//    public PedidoDTO updateById(@RequestBody PedidoDTO dto, @PathVariable Long id){
//        return pedidoService.updateById(dto, id);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(code = HttpStatus.NO_CONTENT)
//    public void deleteById(@PathVariable("id")Long id){
//        pedidoService.deleteById(id);
//    }
//
//    @GetMapping("/{id}")
//    public PedidoDTO searchById(@PathVariable("id") Long id) {
//        return pedidoService.searchCategoryById(id);
//    }
//}
