package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.service.CustomerService;
import br.com.rd.MestreDasFacas.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO dto) throws SQLIntegrityConstraintViolationException {
        return customerService.add(dto);
    }

    @GetMapping
    public List<CustomerDTO> findAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable("id") Long id){
        return customerService.findById(id);
    }

    @GetMapping("/select/{email}")
    public CustomerDTO findByEmail(@PathVariable("email") String email){

        return customerService.findByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        customerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateById(@PathVariable("id") Long id, @RequestBody CustomerDTO dto) throws Exception {
        return customerService.update(id, dto);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changeUserPassword(@RequestBody PasswordDTO dto){
        if(customerService.changePassword(dto)){
            return new ResponseEntity<>("Senha alterada com sucesso", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Senha incorreta", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/removeAddress")
    public CustomerDTO removeAddressFromCustomer(@RequestParam("customer") Long customerId, @RequestParam("address") Long addressId){
        return customerService.removeAddressFromCustomer(customerId, addressId);
    }

    @PutMapping("/addAddress/{customerId}")
    public CustomerDTO addAddressToCustomer(@RequestBody AddressDTO address, @PathVariable("customerId") Long customerId){
        return customerService.addAddressToCustomer(address, customerId);
    }

    @PutMapping("/removeTelephone")
    public CustomerDTO removeTelephoneFromCustomer(@RequestParam("customer") Long customerId, @RequestParam("telephone") Long telephoneId){
        return customerService.removeTelephoneFromCustomer(customerId, telephoneId);
    }

    @PutMapping("/addTelephone/{customerId}")
    public CustomerDTO addTelephoneToCustomer(@RequestBody TelephoneDTO telephone, @PathVariable("customerId") Long customerId){
        return customerService.addTelephoneToCustomer(telephone, customerId);
    }

    @PutMapping("/removeCreditCard")
    public CustomerDTO removeCreditCardFromCustomer(@RequestParam("customer") Long customerId, @RequestParam("creditCard") Long creditCardId){
        return customerService.removeCreditCardFromCustomer(customerId, creditCardId);
    }

    @PutMapping("/addCreditCard/{customerId}")
    public CustomerDTO addCreditCardToCustomer(@RequestBody CreditCardDTO creditCard, @PathVariable("customerId") Long customerId){
        return customerService.addCreditCardToCustomer(creditCard, customerId);
    }
}
