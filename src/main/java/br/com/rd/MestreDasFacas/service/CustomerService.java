package br.com.rd.MestreDasFacas.service;


import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.*;
import br.com.rd.MestreDasFacas.repository.contract.*;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    DtoConversion conversion;

    @Autowired
    PasswordEncoder encoder;

    public CustomerDTO add(CustomerDTO dto){
        Customer newCustomer = conversion.customerDtoToBusiness(dto);

        //encriptar senha
        String passwordCrypt = encoder.encode(dto.getPassword());
        newCustomer.setPassword(passwordCrypt);

        newCustomer = customerRepository.save(newCustomer);
        return conversion.customerBusinessToDto(newCustomer);
    }

    public CustomerDTO update(Long id, CustomerDTO dto) {
        Optional<Customer> op = customerRepository.findById(id);
        if(op.isPresent()){
            Customer update = op.get();

            if(dto.getName() != null){
                update.setName(dto.getName());
            }
            if(dto.getEmail() != null){
                update.setEmail(dto.getEmail());
            }
            if(dto.getCpf() != null){
                update.setCpf(dto.getCpf());
            }
            if(dto.getBirthDate() != null){
                update.setBirthDate(dto.getBirthDate());
            }
            if(dto.getPassword() != null){
                //encriptar senha
                String passwordCrypt = encoder.encode(dto.getPassword());
                update.setPassword(passwordCrypt);
            }
            if(dto.getGender() != null){
                Gender gender = conversion.genderDtoToBusiness(dto.getGender());
                update.setGender(gender);
            }

            update = customerRepository.save(update);
            return conversion.customerBusinessToDto(update);
        }
        return null;
    }

    public CustomerDTO findById(Long id){
        Optional<Customer> op = customerRepository.findById(id);
        if(op.isPresent()){
            Customer customer = op.get();
            return conversion.customerBusinessToDto(customer);
        }
        return null;
    }

    public List<CustomerDTO> findAll(){
        List<Customer> list = customerRepository.findAll();

        return conversion.customerListToDto(list);
    }

    public void deleteById(Long id){
        if (customerRepository.existsById(id)){
            customerRepository.deleteById(id);
        }
    }

    public CustomerDTO addAddressToCustomer(AddressDTO addressDTO, Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();
            if(customer.getAddresses() != null){
                List<Address> listAddress = customer.getAddresses();
                Address newAddress = conversion.addressDtoToBusiness(addressDTO);
                newAddress = addressRepository.save(newAddress);

                listAddress.add(newAddress);
                customer.setAddresses(listAddress);
                customer = customerRepository.save(customer);
                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO removeAddressFromCustomer(Long customerId, Long addressId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        Optional<Address> opAddress = addressRepository.findById(addressId);

        if(opCustomer.isPresent() && opAddress.isPresent()){
            Customer customer = opCustomer.get();
            Address address = opAddress.get();

            if(customer.getAddresses() != null){
                List<Address> customerAddressList = customer.getAddresses();

                for(Address a : customerAddressList){
                    if(a.getId() == addressId){
                        customerAddressList.remove(address);
                        break;
                    }
                }
                customer.setAddresses(customerAddressList);
                customer = customerRepository.save(customer);
                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO addTelephoneToCustomer(TelephoneDTO dto, Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();
            if(customer.getTelephones() != null){
                List<Telephone> telephoneList = customer.getTelephones();
                Telephone newTelephone = conversion.telephoneDtoToBusiness(dto);

                newTelephone = telephoneRepository.save(newTelephone);
                telephoneList.add(newTelephone);
                customer.setTelephones(telephoneList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO removeTelephoneFromCustomer(Long customerId, Long telephoneId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        Optional<Telephone> opTelephone = telephoneRepository.findById(telephoneId);

        if(opCustomer.isPresent() && opTelephone.isPresent()){
            Customer customer = opCustomer.get();
            Telephone telephone = opTelephone.get();

            if(customer.getTelephones() != null){
                List<Telephone> telephoneList = customer.getTelephones();

                for (Telephone t : telephoneList){
                    if(t.getId() == telephone.getId()){
                        telephoneList.remove(telephone);
                        break;
                    }
                }

                customer.setTelephones(telephoneList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO addCreditCardToCustomer(CreditCardDTO dto, Long customerId){
        Optional<Customer> op = customerRepository.findById(customerId);

        if(op.isPresent()){
            Customer customer = op.get();

            if(customer.getCreditCards() != null){
                List<CreditCard> creditCardList = customer.getCreditCards();
                CreditCard newCard = conversion.creditCardDtoToBusiness(dto);

                newCard = creditCardRepository.save(newCard);
                creditCardList.add(newCard);
                customer.setCreditCards(creditCardList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;
    }

    public CustomerDTO removeCreditCardFromCustomer(Long customerId, Long creditCardId){
        Optional<Customer> opCustomer = customerRepository.findById(customerId);
        Optional<CreditCard> opCreditCard = creditCardRepository.findById(creditCardId);

        if(opCustomer.isPresent() && opCreditCard.isPresent()){
            Customer customer = opCustomer.get();
            CreditCard card = opCreditCard.get();

            if(customer.getCreditCards() != null){
                List<CreditCard> creditCardList = customer.getCreditCards();

                for (CreditCard c : creditCardList){
                    if(c.getId() == card.getId()){
                        creditCardList.remove(card);
                        break;
                    }
                }

                customer.setCreditCards(creditCardList);
                customer = customerRepository.save(customer);

                return conversion.customerBusinessToDto(customer);
            }
        }
        return null;

    }
}
