package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.CreditCardDTO;
import br.com.rd.MestreDasFacas.model.dto.CustomerCreditCardDTO;
import br.com.rd.MestreDasFacas.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditCards")
public class CreditCardController {
    @Autowired
    CreditCardService creditCardService;

    @GetMapping("/{id}")
    public CreditCardDTO findById(@PathVariable("id") Long id){
        return creditCardService.findById(id);
    }

    @GetMapping("/showAllByCustomer/{customerId}")
    public CustomerCreditCardDTO showAllCreditCardsFromCustomer(@PathVariable("customerId") Long customerId){
        return creditCardService.showAllCreditCardFromCustomer(customerId);
    }

    @PutMapping("/{id}")
    public CreditCardDTO updateCreditCard(@RequestBody CreditCardDTO dto, @PathVariable("id") Long id){
        return creditCardService.updateCreditCard(dto, id);
    }
}
