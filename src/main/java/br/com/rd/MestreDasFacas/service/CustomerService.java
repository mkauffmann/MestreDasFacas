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


    public CustomerDTO add(CustomerDTO dto){
        Customer newCustomer = conversion.customerDtoToBusiness(dto);
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
                update.setPassword(dto.getPassword());
            }
            if(dto.getGender() != null){
                Gender gender = conversion.genderDtoToBusiness(dto.getGender());
                update.setGender(gender);
            }

            if(dto.getTelephones() != null){
                List<Telephone> listTel = conversion.telephoneListFromDto(dto.getTelephones());
                List<Telephone> listUpdate = update.getTelephones();

                for (Telephone t : listTel){
                    t = telephoneRepository.save(t);
                    listUpdate.add(t);
                }
                update.setTelephones(listUpdate);
            }
            if(dto.getAddresses() != null){
                List<Address> listAdd = conversion.addressListFromDto(dto.getAddresses());
                List<Address> listUpdate = update.getAddresses();
                for (Address a : listAdd){
                    a = addressRepository.save(a);
                    listUpdate.add(a);
                }
                update.setAddresses(listUpdate);
            }
            if(dto.getCreditCards() != null){
                List<CreditCard> listCard = conversion.creditCardListFromDto(dto.getCreditCards());
                List<CreditCard> listUpdate = update.getCreditCards();
                for (CreditCard c : listCard){
                    c = creditCardRepository.save(c);
                    listUpdate.add(c);
                }
                update.setCreditCards(listUpdate);
            }

//            if(dto.getTelephones() != null){
//                List<Telephone> listTel = conversion.telephoneListFromDto(dto.getTelephones());
//                List<Telephone> listUpdate = update.getTelephones();
//
//                for (Telephone t : listTel){
//                    t = telephoneRepository.save(t);
//                    listUpdate.add(t);
//                }
//                update.setTelephones(listUpdate);
//            }
//
//            if(dto.getCreditCards() != null){
//                List<CreditCard> listCard = conversion.creditCardListFromDto(dto.getCreditCards());
//                List<CreditCard> listUpdate = update.getCreditCards();
//                for (CreditCard c : listCard){
//                    c = creditCardRepository.save(c);
//                    listUpdate.add(c);
//                }
//                update.setCreditCards(listUpdate);
//            }


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


    public CustomerDTO addAddressToUser(AddressDTO addressDTO, Long customerId){
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

}
