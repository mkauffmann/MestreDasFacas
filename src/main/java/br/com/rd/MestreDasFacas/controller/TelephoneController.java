package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.CustomerTelephoneDTO;
import br.com.rd.MestreDasFacas.model.dto.TelephoneDTO;
import br.com.rd.MestreDasFacas.service.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telephones")
public class TelephoneController {
    @Autowired
    TelephoneService telephoneService;

    @GetMapping("/{id}")
    public TelephoneDTO findTelephoneById(@PathVariable("id") Long id){
        return telephoneService.findById(id);
    }

    @GetMapping("/showAllByCustomer/{customerId}")
    public CustomerTelephoneDTO showAllTelephonesFromCustomer(@PathVariable("customerId") Long customerId){
        return telephoneService.showAllCustomerTelephones(customerId);
    }

    @PutMapping("/{id}")
    public TelephoneDTO updateTelephone(@RequestBody TelephoneDTO dto, @PathVariable("id") Long id){
        return telephoneService.updateTelephone(dto, id);
    }

}
