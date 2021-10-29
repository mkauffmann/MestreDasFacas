package br.com.rd.MestreDasFacas.controller;

import br.com.rd.MestreDasFacas.model.dto.AddressDTO;
import br.com.rd.MestreDasFacas.model.dto.CustomerAddressDTO;
import br.com.rd.MestreDasFacas.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/{id}")
    public AddressDTO findById(@PathVariable("id") Long id){
        return addressService.findById(id);
    }

    @GetMapping("/showAllByCustomer/{id}")
    public CustomerAddressDTO showAllByCustomerId(@PathVariable("id") Long id){
        return addressService.showAllCustomerAddresses(id);
    }

    @PutMapping("/{id}")
    public AddressDTO updateAddress(@PathVariable("id") Long id, @RequestBody AddressDTO dto){
        return addressService.updateAddress(dto, id);
    }
}
