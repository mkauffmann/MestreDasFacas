package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.CustomerTelephoneDTO;
import br.com.rd.MestreDasFacas.model.dto.TelephoneDTO;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.model.entity.Telephone;
import br.com.rd.MestreDasFacas.repository.contract.CustomerRepository;
import br.com.rd.MestreDasFacas.repository.contract.TelephoneRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TelephoneService {
    @Autowired
    DtoConversion conversion;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    CustomerRepository customerRepository;

    public TelephoneDTO findById(Long id){
        Optional<Telephone> op = telephoneRepository.findById(id);

        if(op.isPresent()){
            Telephone telephone = op.get();
            return conversion.telephoneBusinessToDto(telephone);
        }
        return null;
    }

    public TelephoneDTO updateTelephone(TelephoneDTO dto, Long id){
        Optional<Telephone> op = telephoneRepository.findById(id);

        if(op.isPresent()){
            Telephone updateTelephone = op.get();

            if(dto.getDdd() != null){
                updateTelephone.setDdd(dto.getDdd());
            }
            if(dto.getPhoneNumber() != null){
                updateTelephone.setPhoneNumber(dto.getPhoneNumber());
            }

            updateTelephone = telephoneRepository.save(updateTelephone);
            return conversion.telephoneBusinessToDto(updateTelephone);
        }

        return null;
    }

    public CustomerTelephoneDTO showAllCustomerTelephones(Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();
            CustomerTelephoneDTO dto = new CustomerTelephoneDTO();

            dto.setCustomerId(customer.getId());
            dto.setCustomerName(customer.getName());

            if(!customer.getTelephones().isEmpty()){
                dto.setTelephones(conversion.telephoneListToDto(customer.getTelephones()));
            }

            return dto;
        }
        return null;
    }
}
