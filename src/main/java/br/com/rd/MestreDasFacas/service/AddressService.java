package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.AddressDTO;
import br.com.rd.MestreDasFacas.model.dto.CustomerAddressDTO;
import br.com.rd.MestreDasFacas.model.entity.Address;
import br.com.rd.MestreDasFacas.model.entity.City;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.model.entity.State;
import br.com.rd.MestreDasFacas.repository.contract.AddressRepository;
import br.com.rd.MestreDasFacas.repository.contract.CustomerRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    DtoConversion conversion;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    public AddressDTO findById(Long id){
        Optional<Address> op = addressRepository.findById(id);
        if(op.isPresent()){
            Address address = op.get();
            return conversion.addressBusinessToDto(address);
        }
        return null;
    }


    public AddressDTO updateAddress(AddressDTO dto, Long id){
        Optional<Address> op = addressRepository.findById(id);
        if(op.isPresent()){
            Address updateAddress = op.get();

            if(dto.getStreet() != null){
                updateAddress.setStreet(dto.getStreet());
            }
            if(dto.getNumber() != null){
                updateAddress.setNumber(dto.getNumber());
            }
            if(dto.getComplement() != null){
                updateAddress.setComplement(dto.getComplement());
            }
            if(dto.getCep() != null){
                updateAddress.setCep(dto.getCep());
            }
            if(dto.getNeighborhood() != null){
                updateAddress.setNeighborhood(dto.getNeighborhood());
            }
            if(dto.getCity() != null){
                City updateCity = conversion.cityDtoToBusiness(dto.getCity());
                updateAddress.setCity(updateCity);
            }
            if(dto.getState() != null){
                State updateState = conversion.stateDtoToBusiness(dto.getState());
                updateAddress.setState(updateState);
            }

            updateAddress = addressRepository.save(updateAddress);
            return conversion.addressBusinessToDto(updateAddress);
        }
        return null;
    }

    public CustomerAddressDTO showAllCustomerAddresses(Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();
            CustomerAddressDTO dto = new CustomerAddressDTO();

            dto.setCustomerId(customer.getId());
            dto.setCustomerName(customer.getName());

            if(!customer.getAddresses().isEmpty()){
                dto.setAddresses(conversion.addressListToDto(customer.getAddresses()));
            }
            return dto;
        }
        return null;
    }
}
