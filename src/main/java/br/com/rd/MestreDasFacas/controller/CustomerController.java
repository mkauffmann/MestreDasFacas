package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.AddressDTO;
import br.com.rd.MestreDasFacas.model.dto.CustomerDTO;
import br.com.rd.MestreDasFacas.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public CustomerDTO updateById(@PathVariable("id") Long id, @RequestBody CustomerDTO dto){
       return customerService.update(id, dto);
    }

    @PutMapping("/removeAddress")
    public CustomerDTO removeAddressFromCustomer(@RequestParam("customer") Long customerId, @RequestParam("address") Long addressId){
        return customerService.removeAddressFromCustomer(customerId, addressId);
    }

    @PutMapping("/addAddress/{id}")
    public CustomerDTO addAddressToCustomer(@RequestBody AddressDTO address, @PathVariable("id") Long customerId){
        return customerService.addAddressToUser(address, customerId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        customerService.deleteById(id);
    }
}
