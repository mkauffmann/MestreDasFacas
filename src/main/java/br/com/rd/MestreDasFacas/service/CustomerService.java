package br.com.rd.MestreDasFacas.service;


import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.*;
import br.com.rd.MestreDasFacas.repository.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CardBrandRepository cardBrandRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    GenderRepository genderRepository;

//    public CustomerDTO add(CustomerDTO dto){
//
//    }
//
//    public CustomerDTO update(Long id, CustomerDTO dto){
//
//    }
//
//    public CustomerDTO findById(Long id){
//
//    }
//
//    public List<CustomerDTO> findAll(){
//
//    }
//
//    public void deleteById(Long id){
//
//    }
//
//    //CONVERSOES LISTAS INICIO
//     private List<CustomerDTO> customerListToDto(List<Customer> list){
//
//     }
//
//    private List<TelephoneDTO> telephoneListToDto(List<Telephone> list){
//
//    }

    private List<Telephone> telephoneListFromDto(List<TelephoneDTO> dtoList){
        List<Telephone> listTel = new ArrayList<Telephone>();

        for (TelephoneDTO dto : dtoList){
            listTel.add(telephoneDtoToBusiness(dto));
        }

        return listTel;
    }

//    private List<AddressDTO> addressListToDto(List<Address> list){
//
//    }

    private List<Address> addressListFromDto(List<AddressDTO> dtoList){
        List<Address> listAddress = new ArrayList<Address>();

        for (AddressDTO dto : dtoList){
            listAddress.add(addressDtoToBusiness(dto));
        }

        return listAddress;
    }

//    private List<CreditCardDTO> creditCardListToDto(List<CreditCard> list){
//
//    }

    private List<CreditCard> creditCardListFromDto(List<CreditCardDTO> dtoList){
        List<CreditCard> listCard = new ArrayList<CreditCard>();

        for(CreditCardDTO dto : dtoList){
            listCard.add(creditCardDtoToBusiness(dto));
        }
        return listCard;
    }

     //CONVERSOES LISTAS FIM

     //CONVERSOES BUSINESS TO DTO INICIO
//     private CustomerDTO customerBusinessToDto(Customer customer){
//
//     }
//
//     private GenderDTO genderBusinessToDto(Gender gender){
//
//     }
//
//    private TelephoneDTO telephoneBusinessToDto(Telephone telephone){
//
//    }
//
//
//    private AddressDTO addressBusinessToDto(Address address){
//
//    }
//
//    private CreditCardDTO creditCardBusinessToDto(CreditCard creditCard){
//
//    }
//
//    private CardBrandDTO cardBrandBusinessToDto(CardBrand brand){
//
//    }

    //CONVERSOES BUSINESS TO DTO FIM

    //CONVERSORES DTO TO BUSINESS INICIO
     private Customer customerDtoToBusiness(CustomerDTO dto){
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setCpf(dto.getCpf());
         customer.setPassword(dto.getPassword());

        if(dto.getBirthDate() != null){
            customer.setBirthDate(dto.getBirthDate());
        }

        if(dto.getGender() != null){
            Gender gender = genderDtoToBusiness(dto.getGender());
            customer.setGender(gender);
        }

        List<Telephone> telephones = telephoneListFromDto(dto.getTelephones());
        customer.setTelephones(telephones);

        List<Address> addresses = addressListFromDto(dto.getAddresses());
        customer.setAddresses(addresses);

        List<CreditCard> cards = creditCardListFromDto(dto.getCreditCards());
        customer.setCreditCards(cards);

        return customer;
     }

    private Gender genderDtoToBusiness(GenderDTO dto){
        if(dto.getId() != null){
            Optional<Gender> op = genderRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }
        Optional<Gender> op = genderRepository.findByDescription(dto.getDescription());
        if(op.isPresent()){
            return op.get();
        } else {
            Gender gender = new Gender();
            gender.setDescription(dto.getDescription());
            return gender;
        }
    }

    private Telephone telephoneDtoToBusiness(TelephoneDTO dto){
        if(dto.getId() != null){
            Optional<Telephone> op = telephoneRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }

        //checar se telefone existe
        Optional<Telephone> op = telephoneRepository.findTelephone(dto.getDdd(), dto.getPhoneNumber());
        if(op.isPresent()){
            return op.get();
        } else {
            Telephone tel = new Telephone();
            tel.setDdd(dto.getDdd());
            tel.setPhoneNumber(dto.getPhoneNumber());
            return tel;
        }
    }

    //ADDRESS INICIO
    private Address addressDtoToBusiness(AddressDTO dto){
        //se foi passado id
        if(dto.getId() != null){
            Optional<Address> op = addressRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }
        //se nao foi passado um id
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setNumber(dto.getNumber());
        if(dto.getComplement() != null){
            address.setComplement(dto.getComplement());
        }
        address.setCep(dto.getCep());
        address.setNeighborhood(dto.getNeighborhood());

        City city = cityDtoToBusiness(dto.getCity());
        address.setCity(city);

        State state = stateDtoToBusiness(dto.getState());
        address.setState(state);

        return address;
    }

    private City cityDtoToBusiness(CityDTO dto){
        //se foi passado um id
        if(dto.getId() != null){
            Optional<City> op = cityRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }

        //checar se cidade já existe na base
        Optional<City> op = cityRepository.findCityByName(dto.getCityName());
        if(op.isPresent()){
            return op.get();
        } else {
            City city = new City();
            city.setCityName(dto.getCityName());
            return city;
        }

    }

    private State stateDtoToBusiness(StateDTO dto){
        State state = stateRepository.getById(dto.getUf());
        return state;
    }

    //ADDRESS FIM

    //CREDIT CARD INICIO
    private CreditCard creditCardDtoToBusiness(CreditCardDTO dto){
        CreditCard creditCard = new CreditCard();

        if(dto.getId() != null){
            Optional<CreditCard> op = creditCardRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }

        CardBrand brandDto = cardBrandDtoToBusiness(dto.getCardBrand());
        creditCard.setCardBrand(brandDto);
        creditCard.setHolderName(dto.getHolderName());
        creditCard.setCpf(dto.getCpf());
        creditCard.setCardNumber(dto.getCardNumber());

        return creditCard;


    }

    private CardBrand cardBrandDtoToBusiness(CardBrandDTO dto){
        CardBrand brand = new CardBrand();

        if(dto.getId() != null){
            Optional<CardBrand> op = cardBrandRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }
        brand.setCardBrandName(dto.getCardBrandName());
        return brand;
    }
    //CREDIT CARD FIM

    //CONVERSORES DTO TO BUSINESS FIM
}
