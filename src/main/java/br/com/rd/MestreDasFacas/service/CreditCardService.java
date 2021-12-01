package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.CardBrandDTO;
import br.com.rd.MestreDasFacas.model.dto.CreditCardDTO;
import br.com.rd.MestreDasFacas.model.dto.CustomerCreditCardDTO;
import br.com.rd.MestreDasFacas.model.entity.CardBrand;
import br.com.rd.MestreDasFacas.model.entity.CreditCard;
import br.com.rd.MestreDasFacas.model.entity.Customer;
import br.com.rd.MestreDasFacas.repository.contract.CreditCardRepository;
import br.com.rd.MestreDasFacas.repository.contract.CustomerRepository;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardService {
    @Autowired
    DtoConversion conversion;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerRepository customerRepository;

    public CreditCardDTO findById(Long id){
        Optional<CreditCard> op = creditCardRepository.findById(id);

        if(op.isPresent()){
            return conversion.creditCardBusinessToDto(op.get());
        }
        return null;
    }

    public CreditCardDTO updateCreditCard(CreditCardDTO dto, Long id){
        Optional<CreditCard> op = creditCardRepository.findById(id);

        if(op.isPresent()){
            CreditCard updateCard = op.get();


            if(dto.getCpf() != null){
                updateCard.setCpf(dto.getCpf());
            }
            if(dto.getHolderName() != null){
                updateCard.setHolderName(dto.getHolderName());
            }

            updateCard = creditCardRepository.save(updateCard);
            return conversion.creditCardBusinessToDto(updateCard);
        }

        return null;

    }

    public CustomerCreditCardDTO showAllCreditCardFromCustomer(Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();
            CustomerCreditCardDTO dto = new CustomerCreditCardDTO();

            dto.setCustomerId(customer.getId());
            dto.setCustomerName(customer.getName());
            dto.setCreditCards(conversion.creditCardListToDto(customer.getCreditCards()));

            return dto;
        }
        return null;
    }

}
