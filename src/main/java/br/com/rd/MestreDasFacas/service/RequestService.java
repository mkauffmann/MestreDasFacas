package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.enums.StatusEmail;
import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.*;
import br.com.rd.MestreDasFacas.repository.contract.*;
import br.com.rd.MestreDasFacas.service.conversion.DtoConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    DeliveryStatusRepository deliveryStatusRepository;

    @Autowired
    TypePaymentRepository typePaymentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    TelephoneRepository telephoneRepository;

    @Autowired
    CardBrandRepository cardBrandRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    DtoConversion conversion;

    @Autowired
    ItemRequestRepository itemRequestRepository;

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    InventoryRepository inventoryRepository;



    // Create

    public RequestDTO addRequest(RequestDTO dto) {
        Request newRequest = dtoToBusiness(dto);
        newRequest = requestRepository.save(newRequest);

        for (ItemRequest i : newRequest.getItemrequests()) {
            Product product = i.getProduct();
            Optional<Inventory> op = inventoryRepository.myFindById(product.getId());

            if(op.isPresent()) {
                Inventory obj = op.get();
                Integer qtdEstoque = obj.getQuantityInventory();
                Integer qtdCompra = Math.toIntExact(i.getQuantity());
                obj.setQuantityInventory(qtdEstoque - qtdCompra);
//                inventoryRepository.myInventoryUpdate(obj.getId());
                inventoryRepository.save(obj);
            }

//            Product product = i.getProduct();
//            inventoryRepository.findById(id);
        }

        return businessToDto(newRequest);
    }

    // Show all

    public List<RequestDTO> findAll() {
        List<Request> list = requestRepository.findAll();
        return listToDto(list);
    }

    // Update by id

    public RequestDTO update(RequestDTO dto, Long id) {
        Optional<Request> op = requestRepository.findById(id);

        if (op.isPresent()) {

            Request obj = op.get();

            if (dto.getFreightFixed() != null) {
                obj.setFreightFixed(dto.getFreightFixed());
            }

            if (dto.getPaymentDate() != null) {
                obj.setPaymentDate(dto.getPaymentDate());
            }

            if (dto.getPurchaseDate() != null) {
                obj.setPaymentDate(dto.getPaymentDate());
            }


            if (dto.getDeliveryStatus() != null) {
                DeliveryStatus newDeliveryStatus = deliveryDtoToBusiness(dto.getDeliveryStatus());
                obj.setDeliveryStatus(newDeliveryStatus);
            }

            if (dto.getTypePayment() != null) {
                TypePayment newPayment = typePaymentDtoToBusiness(dto.getTypePayment());
                obj.setTypePayment(newPayment);

            }if(dto.getAddress() !=null){
                Address newAdress = conversion.addressDtoToBusiness(dto.getAddress());
                obj.setAddress(newAdress);
            }
            if(dto.getItemRequest() !=null){
                List<ItemRequest> itemRequest = conversion.itemRequestListFromDto(dto.getItemRequest());
                List<ItemRequest> listUpdate = obj.getItemrequests();

                for(ItemRequest i : itemRequest) {
                    i = itemRequestRepository.save(i);
                    listUpdate.add(i);



                }
                obj.setItemrequests(listUpdate);

            }

            requestRepository.save(obj);
            return businessToDto(obj);

        }
        return null;
    }

    //delete by id

    public void deleteById(Long id) {
        if (requestRepository.existsById(id)) {
            requestRepository.deleteById(id);
        }
    }

    // Show by id

    public RequestDTO findById(Long id) {
        Optional<Request> op = requestRepository.findById(id);

        if (op.isPresent()) {
            return businessToDto(op.get());
        }

        return null;
    }

    public List<RequestDTO> listAllByCustomers(Long id) {
        List<Request> requestList = this.requestRepository.myFindAllRequestByCustomer(id);
        return listToDto(requestList);
    }

    //Convertion;

    //deliveryStatus

    private DeliveryStatus deliveryDtoToBusiness(DeliveryStatusDTO dto) {
        DeliveryStatus business = new DeliveryStatus();

        if (dto.getId() != null) {
            Long deliveryId = dto.getId();
            if (deliveryStatusRepository.existsById(deliveryId)) {
                business = deliveryStatusRepository.getById(deliveryId);
            } else {
                business.setDescription_status_delivery(dto.getDescription_status_delivery());
            }
        } else {
            business.setDescription_status_delivery(dto.getDescription_status_delivery());
        }
        return business;
    }

    private DeliveryStatusDTO deliveryBusinessToDto(DeliveryStatus business) {

        DeliveryStatusDTO dto = new DeliveryStatusDTO();

        dto.setId(business.getId());
        dto.setDescription_status_delivery(business.getDescription_status_delivery());

        return dto;

    }

    //TypePayment

    private TypePayment typePaymentDtoToBusiness(TypePaymentDTO dto) {

        TypePayment business = new TypePayment();

        if (dto.getId() != null) {

            Long paymentId = dto.getId();
            if (typePaymentRepository.existsById(paymentId)) {
                business = typePaymentRepository.getById(paymentId);
            } else {
                business.setDescription_type_payment(dto.getDescription_type_payment());
            }
        } else {
            business.setDescription_type_payment(dto.getDescription_type_payment());
        }

        return business;

    }



    private TypePaymentDTO typePaymentBusinessToDto(TypePayment business) {

        TypePaymentDTO dto = new TypePaymentDTO();

        dto.setId(business.getId());
        dto.setDescription_type_payment(business.getDescription_type_payment());

        return dto;
    }

    //////////////////////////////////////

//    private CreditCard creditCardDtoToBusiness(CreditCardDTO dto){
//        CreditCard creditCard = new CreditCard();
//
//        if(dto.getId() != null){
//            Optional<CreditCard> op = creditCardRepository.findById(dto.getId());
//            if(op.isPresent()){
//                return op.get();
//            }
//        }
//
//        CardBrand brandDto = cardBrandDtoToBusiness(dto.getCardBrand());
//        creditCard.setCardBrand(brandDto);
//        creditCard.setHolderName(dto.getHolderName());
//        creditCard.setCpf(dto.getCpf());
//        creditCard.setCardNumber(dto.getCardNumber());
//
//        return creditCard;
//
//
//    }
//
//    private CardBrand cardBrandDtoToBusiness(CardBrandDTO dto){
//        //se foi passado id
//        if(dto.getId() != null){
//            Optional<CardBrand> op = cardBrandRepository.findById(dto.getId());
//            if(op.isPresent()){
//                return op.get();
//            }
//        }
//        //checar se nome já existe
//        Optional<CardBrand> op = cardBrandRepository.findByCardBrandName(dto.getCardBrandName());
//        if(op.isPresent()){
//            return op.get();
//        } else {
//            CardBrand brand = new CardBrand();
//            brand.setCardBrandName(dto.getCardBrandName());
//            return brand;
//        }
//    }
//    private Gender genderDtoToBusiness(GenderDTO dto){
//        if(dto.getId() != null){
//            Optional<Gender> op = genderRepository.findById(dto.getId());
//            if(op.isPresent()){
//                return op.get();
//            }
//        }
//        Optional<Gender> op = genderRepository.findByDescription(dto.getDescription());
//        if(op.isPresent()){
//            return op.get();
//        } else {
//            Gender gender = new Gender();
//            gender.setDescription(dto.getDescription());
//            return gender;
//        }
//    }
//
//    private Telephone telephoneDtoToBusiness(TelephoneDTO dto){
//        if(dto.getId() != null){
//            Optional<Telephone> op = telephoneRepository.findById(dto.getId());
//            if(op.isPresent()){
//                return op.get();
//            }
//        }
//
//        //checar se telefone existe
//        Optional<Telephone> op = telephoneRepository.findTelephone(dto.getDdd(), dto.getPhoneNumber());
//        if(op.isPresent()){
//            return op.get();
//        } else {
//            Telephone tel = new Telephone();
//            tel.setDdd(dto.getDdd());
//            tel.setPhoneNumber(dto.getPhoneNumber());
//            return tel;
//        }
//    }
//
//
//    private CreditCardDTO creditCardBusinessToDto(CreditCard creditCard){
//        CreditCardDTO dto = new CreditCardDTO();
//
//        dto.setId(creditCard.getId());
//        dto.setCardNumber(creditCard.getCardNumber());
//        dto.setCpf(creditCard.getCpf());
//        dto.setHolderName(creditCard.getHolderName());
//
//        CardBrandDTO brandDto = cardBrandBusinessToDto(creditCard.getCardBrand());
//        dto.setCardBrand(brandDto);
//
//        return dto;
//    }
//
//    private CardBrandDTO cardBrandBusinessToDto(CardBrand brand){
//        CardBrandDTO dto = new CardBrandDTO();
//
//        dto.setId(brand.getId());
//        dto.setCardBrandName(brand.getCardBrandName());
//
//        return dto;
//    }
//
//    private GenderDTO genderBusinessToDto(Gender gender){
//        GenderDTO dto = new GenderDTO();
//
//        dto.setId(gender.getId());
//        dto.setDescription(gender.getDescription());
//
//        return dto;
//    }
//
//    private TelephoneDTO telephoneBusinessToDto(Telephone telephone){
//        TelephoneDTO dto = new TelephoneDTO();
//
//        dto.setId(telephone.getId());
//        dto.setDdd(telephone.getDdd());
//        dto.setPhoneNumber(telephone.getPhoneNumber());
//
//        return dto;
//    }
//
//
//    private List<TelephoneDTO> telephoneListToDto(List<Telephone> list){
//        List<TelephoneDTO> listDto = new ArrayList<TelephoneDTO>();
//
//        for (Telephone t : list){
//            listDto.add(telephoneBusinessToDto(t));
//        }
//        return listDto;
//    }
//
//    private List<Address> addressListFromDto(List<AddressDTO> dtoList){
//        List<Address> listAddress = new ArrayList<Address>();
//
//        for (AddressDTO dto : dtoList){
//            listAddress.add(addressDtoToBusiness(dto));
//        }
//
//        return listAddress;
//    }
//
//    private List<CreditCardDTO> creditCardListToDto(List<CreditCard> list){
//        List<CreditCardDTO> listDto = new ArrayList<CreditCardDTO>();
//
//        for(CreditCard card : list){
//            listDto.add(creditCardBusinessToDto(card));
//        }
//        return listDto;
//    }
//
//    private List<CreditCard> creditCardListFromDto(List<CreditCardDTO> dtoList){
//        List<CreditCard> listCard = new ArrayList<CreditCard>();
//
//        for(CreditCardDTO dto : dtoList){
//            listCard.add(creditCardDtoToBusiness(dto));
//        }
//        return listCard;
//    }
//
//    private List<CustomerDTO> customerListToDto(List<Customer> list){
//        List<CustomerDTO> listDto = new ArrayList<CustomerDTO>();
//
//        for (Customer c : list){
//            listDto.add(customerBusinessToDto(c));
//        }
//        return listDto;
//    }
//
//
//    private List<Telephone> telephoneListFromDto(List<TelephoneDTO> dtoList){
//        List<Telephone> listTel = new ArrayList<Telephone>();
//
//        for (TelephoneDTO dto : dtoList){
//            listTel.add(telephoneDtoToBusiness(dto));
//        }
//
//        return listTel;
//    }
//
//    private List<AddressDTO> addressListToDto(List<Address> list){
//        List<AddressDTO> listDto = new ArrayList<AddressDTO>();
//
//        for(Address a : list){
//            listDto.add(addressBusinessToDto(a));
//        }
//        return listDto;
//    }
//
//    private Customer customerDtoToBusiness(CustomerDTO dto){
//        //se foi passado um id
//        if(dto.getId() != null){
//            Optional<Customer> op = customerRepository.findById(dto.getId());
//            if(op.isPresent()){
//                return op.get();
//            }
//        }
//        Customer customer = new Customer();
//        customer.setName(dto.getName());
//        customer.setEmail(dto.getEmail());
//        customer.setCpf(dto.getCpf());
//        customer.setPassword(dto.getPassword());
//
//        if(dto.getBirthDate() != null){
//            customer.setBirthDate(dto.getBirthDate());
//        }
//
//        if(dto.getGender() != null){
//            Gender gender = genderDtoToBusiness(dto.getGender());
//            customer.setGender(gender);
//        }
//
//        if(dto.getTelephones() != null){
//            List<Telephone> telephones = telephoneListFromDto(dto.getTelephones());
//            customer.setTelephones(telephones);
//        }
//        if(dto.getAddresses() != null){
//            List<Address> addresses = addressListFromDto(dto.getAddresses());
//            customer.setAddresses(addresses);
//        }
//        if(dto.getCreditCards() != null){
//            List<CreditCard> cards = creditCardListFromDto(dto.getCreditCards());
//            customer.setCreditCards(cards);
//        }
//        return customer;
//    }
//
//    private CustomerDTO customerBusinessToDto(Customer customer){
//        CustomerDTO dto = new CustomerDTO();
//
//        dto.setId(customer.getId());
//        dto.setName(customer.getName());
//        dto.setEmail(customer.getEmail());
//        dto.setCpf(customer.getCpf());
//        dto.setPassword(customer.getPassword());
//        if(customer.getBirthDate() != null){
//            dto.setBirthDate(customer.getBirthDate());
//        }
//        if(customer.getGender() != null){
//            GenderDTO gender = genderBusinessToDto(customer.getGender());
//            dto.setGender(gender);
//        }
//
//        if(customer.getTelephones() != null){
//            List<TelephoneDTO> telephones = telephoneListToDto(customer.getTelephones());
//            dto.setTelephones(telephones);
//        }
//        if(customer.getAddresses() != null){
//            List<AddressDTO> addresses = addressListToDto(customer.getAddresses());
//            dto.setAddresses(addresses);
//        }
//        if(customer.getCreditCards() != null){
//            List<CreditCardDTO> creditCards = creditCardListToDto(customer.getCreditCards());
//            dto.setCreditCards(creditCards);
//        }
//
//        return dto;
//    }
//
//
//
//    //////////////////////////////////////
//    public AddressDTO addressBusinessToDto(Address address){
//        AddressDTO dto = new AddressDTO();
//
//        dto.setId(address.getId());
//        dto.setStreet(address.getStreet());
//        dto.setNumber(address.getNumber());
//        if(address.getComplement() != null){
//            dto.setComplement(address.getComplement());
//        }
//        dto.setCep(address.getCep());
//        dto.setNeighborhood(address.getNeighborhood());
//
//        CityDTO cityDto = cityBusinessToDto(address.getCity());
//        dto.setCity(cityDto);
//
//        StateDTO stateDto = stateBusinessToDto(address.getState());
//        dto.setState(stateDto);
//
//        return dto;
//    }
//
//    private CityDTO cityBusinessToDto(City city){
//        CityDTO dto = new CityDTO();
//
//        dto.setId(city.getId());
//        dto.setCityName(city.getCityName());
//
//        return dto;
//    }
//
//    private StateDTO stateBusinessToDto(State state){
//        StateDTO dto = new StateDTO();
//
//        dto.setUf(state.getUf());
//        dto.setStateName(state.getStateName());
//
//        return dto;
//    }
//
//    public Address addressDtoToBusiness(AddressDTO dto){
//        //se foi passado id
//        if(dto.getId() != null){
//            Optional<Address> op = addressRepository.findById(dto.getId());
//            if(op.isPresent()){
//                return op.get();
//            }
//        }
//        //se nao foi passado um id
//        Address address = new Address();
//        address.setStreet(dto.getStreet());
//        address.setNumber(dto.getNumber());
//        if(dto.getComplement() != null){
//            address.setComplement(dto.getComplement());
//        }
//        address.setCep(dto.getCep());
//        address.setNeighborhood(dto.getNeighborhood());
//
//        City city = cityDtoToBusiness(dto.getCity());
//        address.setCity(city);
//
//        State state = stateDtoToBusiness(dto.getState());
//        address.setState(state);
//
//        return address;
//    }
//
//    private City cityDtoToBusiness(CityDTO dto){
//        //se foi passado um id
//        if(dto.getId() != null){
//            Optional<City> op = cityRepository.findById(dto.getId());
//            if(op.isPresent()){
//                return op.get();
//            }
//        }
//
//        //checar se cidade já existe na base
//        Optional<City> op = cityRepository.findCityByName(dto.getCityName());
//        if(op.isPresent()){
//            return op.get();
//        } else {
//            City city = new City();
//            city.setCityName(dto.getCityName());
//            return city;
//        }
//
//    }
//
//    private State stateDtoToBusiness(StateDTO dto){
//        State state = stateRepository.getById(dto.getUf());
//        return state;
//    }

    ///////////////////////////////////

    private Double calculateTotalValue(List<ItemRequest> items){
        Double totalValue = 0.0;
        for (ItemRequest item : items){
            totalValue += item.getTotal_value();
        }
        return totalValue;
    }

    private Request dtoToBusiness(RequestDTO dto) {
        Request business = new Request();
        DeliveryStatus deliveryStatus = deliveryDtoToBusiness(dto.getDeliveryStatus());
        TypePayment typePayment = typePaymentDtoToBusiness(dto.getTypePayment());
        Address address = conversion.addressDtoToBusiness(dto.getAddress());
        Customer customer = conversion.customerDtoToBusiness(dto.getCustomer());
        List<ItemRequest> itemRequests = conversion.itemRequestListFromDto(dto.getItemRequest());


        business.setFreightFixed(dto.getFreightFixed());
        if(dto.getPaymentDate() != null) {
            business.setPaymentDate(dto.getPaymentDate());
        }
        business.setPurchaseDate(dto.getPurchaseDate());
        business.setDeliveryStatus(deliveryStatus);
        business.setTypePayment(typePayment);
        business.setAddress(address);
        business.setCustomer(customer);
        business.setItemrequests(itemRequests);

        // Lógica para valor total parcelado:

        business.setInstallments(dto.getInstallments());

        Double valorTotal = calculateTotalValue(itemRequests);
        Double valorTotalParcelado = valorTotal / dto.getInstallments();

        business.setInstallmentsValue(valorTotalParcelado);
        business.setTotalValue(valorTotal);
        business.setFinalValue(business.getFreightFixed() + valorTotal);

        if(dto.getCreditCard() != null){
            CreditCard creditCard = conversion.creditCardDtoToBusiness(dto.getCreditCard());
            business.setCreditCard(creditCard);
        }

        return business;
    }

    private RequestDTO businessToDto(Request business) {

        RequestDTO dto = new RequestDTO();
        TypePaymentDTO typePaymentDTO = typePaymentBusinessToDto(business.getTypePayment());
        DeliveryStatusDTO deliveryDto = deliveryBusinessToDto(business.getDeliveryStatus());
        CustomerDTO customerDTO = conversion.customerBusinessToDto(business.getCustomer());
        AddressDTO addressDTO = conversion.addressBusinessToDto(business.getAddress());
        List <ItemRequestDTO> itemRequestDTO = conversion.itemRequestListToDto(business.getItemrequests());




        dto.setId(business.getId());
        dto.setFreightFixed(business.getFreightFixed());
        dto.setPurchaseDate(business.getPurchaseDate());
        if(business.getPaymentDate() != null){
            dto.setPaymentDate(business.getPaymentDate());
        }
        dto.setTotalValue(business.getTotalValue());
        dto.setFinalValue(business.getFinalValue());
        dto.setTypePayment(typePaymentDTO);
        dto.setDeliveryStatus(deliveryDto);
        dto.setAddress(addressDTO);
        dto.setCustomer(customerDTO);
        dto.setItemRequest(itemRequestDTO);

        dto.setInstallments(business.getInstallments());
        dto.setInstallmentsValue(business.getInstallmentsValue());

        if(business.getCreditCard() != null){
            CreditCardDTO creditCard = conversion.creditCardBusinessToDto(business.getCreditCard());
            dto.setCreditCard(creditCard);
        }

        return dto;
    }

    private List<RequestDTO> listToDto(List<Request> list) {
        List<RequestDTO> listDto = new ArrayList<>();

        for (Request n : list) {
            listDto.add(businessToDto(n));
        }

        return listDto;
    }








}
