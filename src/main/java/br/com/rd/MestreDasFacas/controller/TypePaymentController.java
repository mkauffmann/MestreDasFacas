package br.com.rd.MestreDasFacas.controller;


import br.com.rd.MestreDasFacas.model.dto.TypePaymentDTO;
import br.com.rd.MestreDasFacas.service.TypePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/typePayment")
public class TypePaymentController {
    @Autowired
    TypePaymentService typePaymentService;
    @PostMapping
    public TypePaymentDTO create(@RequestBody TypePaymentDTO typePayment){
        return typePaymentService.addTypePayment(typePayment);
    }

    @GetMapping
    public List<TypePaymentDTO> findAll(){
        return typePaymentService.findAll();
    }

    @PutMapping("/{id}")
    public TypePaymentDTO updateById(@RequestBody TypePaymentDTO dto, @PathVariable Long id){
        return typePaymentService.updateById(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id")Long id){
        typePaymentService.deleteById(id);
    }

    @GetMapping("/{id}")
    public TypePaymentDTO searchById(@PathVariable("id") Long id) {
        return typePaymentService.findTypePaymentById(id);
    }
}
