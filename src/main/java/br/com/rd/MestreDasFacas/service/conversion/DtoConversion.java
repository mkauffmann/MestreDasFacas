package br.com.rd.MestreDasFacas.service.conversion;

import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.*;
import br.com.rd.MestreDasFacas.repository.contract.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DtoConversion {
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

    /*
    *
    * CONVERSORES CUSTOMER INICIO
    *
     */

    //CONVERSOES LISTAS INICIO
    public List<CustomerDTO> customerListToDto(List<Customer> list){
        List<CustomerDTO> listDto = new ArrayList<CustomerDTO>();

        for (Customer c : list){
            listDto.add(customerBusinessToDto(c));
        }
        return listDto;
    }

    public List<TelephoneDTO> telephoneListToDto(List<Telephone> list){
        List<TelephoneDTO> listDto = new ArrayList<TelephoneDTO>();

        for (Telephone t : list){
            listDto.add(telephoneBusinessToDto(t));
        }
        return listDto;
    }

    public List<Telephone> telephoneListFromDto(List<TelephoneDTO> dtoList){
        List<Telephone> listTel = new ArrayList<Telephone>();

        for (TelephoneDTO dto : dtoList){
            listTel.add(telephoneDtoToBusiness(dto));
        }

        return listTel;
    }

    public List<AddressDTO> addressListToDto(List<Address> list){
        List<AddressDTO> listDto = new ArrayList<AddressDTO>();

        for(Address a : list){
            listDto.add(addressBusinessToDto(a));
        }
        return listDto;
    }

    public List<Address> addressListFromDto(List<AddressDTO> dtoList){
        List<Address> listAddress = new ArrayList<Address>();

        for (AddressDTO dto : dtoList){
            listAddress.add(addressDtoToBusiness(dto));
        }

        return listAddress;
    }

    public List<CreditCardDTO> creditCardListToDto(List<CreditCard> list){
        List<CreditCardDTO> listDto = new ArrayList<CreditCardDTO>();

        for(CreditCard card : list){
            listDto.add(creditCardBusinessToDto(card));
        }
        return listDto;
    }

    public List<CreditCard> creditCardListFromDto(List<CreditCardDTO> dtoList){
        List<CreditCard> listCard = new ArrayList<CreditCard>();

        for(CreditCardDTO dto : dtoList){
            listCard.add(creditCardDtoToBusiness(dto));
        }
        return listCard;
    }

    //CONVERSOES LISTAS FIM

    //CONVERSOES BUSINESS TO DTO INICIO
    public CustomerDTO customerBusinessToDto(Customer customer){
        CustomerDTO dto = new CustomerDTO();

        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setCpf(customer.getCpf());
        dto.setPassword(customer.getPassword());
        if(customer.getBirthDate() != null){
            dto.setBirthDate(customer.getBirthDate());
        }
        if(customer.getGender() != null){
            GenderDTO gender = genderBusinessToDto(customer.getGender());
            dto.setGender(gender);
        }

        if(customer.getTelephones() != null){
            List<TelephoneDTO> telephones = telephoneListToDto(customer.getTelephones());
            dto.setTelephones(telephones);
        }
        if(customer.getAddresses() != null){
            List<AddressDTO> addresses = addressListToDto(customer.getAddresses());
            dto.setAddresses(addresses);
        }
        if(customer.getCreditCards() != null){
            List<CreditCardDTO> creditCards = creditCardListToDto(customer.getCreditCards());
            dto.setCreditCards(creditCards);
        }

        return dto;
    }

    public GenderDTO genderBusinessToDto(Gender gender){
        GenderDTO dto = new GenderDTO();

        dto.setId(gender.getId());
        dto.setDescription(gender.getDescription());

        return dto;
    }

    public TelephoneDTO telephoneBusinessToDto(Telephone telephone){
        TelephoneDTO dto = new TelephoneDTO();

        dto.setId(telephone.getId());
        dto.setDdd(telephone.getDdd());
        dto.setPhoneNumber(telephone.getPhoneNumber());

        return dto;
    }


    public AddressDTO addressBusinessToDto(Address address){
        AddressDTO dto = new AddressDTO();

        dto.setId(address.getId());
        dto.setStreet(address.getStreet());
        dto.setNumber(address.getNumber());
        if(address.getComplement() != null){
            dto.setComplement(address.getComplement());
        }
        dto.setCep(address.getCep());
        dto.setNeighborhood(address.getNeighborhood());

        CityDTO cityDto = cityBusinessToDto(address.getCity());
        dto.setCity(cityDto);

        StateDTO stateDto = stateBusinessToDto(address.getState());
        dto.setState(stateDto);

        return dto;
    }

    public CityDTO cityBusinessToDto(City city){
        CityDTO dto = new CityDTO();

        dto.setId(city.getId());
        dto.setCityName(city.getCityName());

        return dto;
    }

    public StateDTO stateBusinessToDto(State state){
        StateDTO dto = new StateDTO();

        dto.setUf(state.getUf());
        dto.setStateName(state.getStateName());

        return dto;
    }

    public CreditCardDTO creditCardBusinessToDto(CreditCard creditCard){
        CreditCardDTO dto = new CreditCardDTO();

        dto.setId(creditCard.getId());
        dto.setCardNumber(creditCard.getCardNumber());
        dto.setCpf(creditCard.getCpf());
        dto.setHolderName(creditCard.getHolderName());

        CardBrandDTO brandDto = cardBrandBusinessToDto(creditCard.getCardBrand());
        dto.setCardBrand(brandDto);

        return dto;
    }

    public CardBrandDTO cardBrandBusinessToDto(CardBrand brand){
        CardBrandDTO dto = new CardBrandDTO();

        dto.setId(brand.getId());
        dto.setCardBrandName(brand.getCardBrandName());

        return dto;
    }

    //CONVERSOES BUSINESS TO DTO FIM

    //CONVERSORES DTO TO BUSINESS INICIO
    public Customer customerDtoToBusiness(CustomerDTO dto){
        //se foi passado um id
        if(dto.getId() != null){
            Optional<Customer> op = customerRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }
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

        if(dto.getTelephones() != null){
            List<Telephone> telephones = telephoneListFromDto(dto.getTelephones());
            customer.setTelephones(telephones);
        }
        if(dto.getAddresses() != null){
            List<Address> addresses = addressListFromDto(dto.getAddresses());
            customer.setAddresses(addresses);
        }
        if(dto.getCreditCards() != null){
            List<CreditCard> cards = creditCardListFromDto(dto.getCreditCards());
            customer.setCreditCards(cards);
        }
        return customer;
    }

    public Gender genderDtoToBusiness(GenderDTO dto){
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

    public Telephone telephoneDtoToBusiness(TelephoneDTO dto){
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
    public Address addressDtoToBusiness(AddressDTO dto){
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

    public City cityDtoToBusiness(CityDTO dto){
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

    public State stateDtoToBusiness(StateDTO dto){
        State state = stateRepository.getById(dto.getUf());
        return state;
    }

    //ADDRESS FIM

    //CREDIT CARD INICIO
    public CreditCard creditCardDtoToBusiness(CreditCardDTO dto){
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

    public CardBrand cardBrandDtoToBusiness(CardBrandDTO dto){
        //se foi passado id
        if(dto.getId() != null){
            Optional<CardBrand> op = cardBrandRepository.findById(dto.getId());
            if(op.isPresent()){
                return op.get();
            }
        }
        //checar se nome já existe
        Optional<CardBrand> op = cardBrandRepository.findByCardBrandName(dto.getCardBrandName());
        if(op.isPresent()){
            return op.get();
        } else {
            CardBrand brand = new CardBrand();
            brand.setCardBrandName(dto.getCardBrandName());
            return brand;
        }
    }
    //CREDIT CARD FIM

    //CONVERSORES DTO TO BUSINESS FIM
    /*
     *
     * CONVERSORES CUSTOMER FIM
     *
     */
}
