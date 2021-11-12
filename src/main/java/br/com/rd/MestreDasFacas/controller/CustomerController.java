package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.service.CustomerService;
import br.com.rd.MestreDasFacas.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping
    public CustomerDTO addCustomer(@RequestBody CustomerDTO dto){

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

    @GetMapping("/select/email}")
    public CustomerDTO findByEmail(@PathVariable("email") String email){
        return customerService.findByEmail(email);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        customerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateById(@PathVariable("id") Long id, @RequestBody CustomerDTO dto){
        return customerService.update(id, dto);
    }

//    @DeleteMapping("/{id}")
//    public void deleteById(@PathVariable("id") Long id){
//        customerService.deleteById(id);
//    }
}
