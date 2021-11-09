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

//    @Autowired
//    GenderRepository genderRepository;
//
//    @Autowired
//    TelephoneRepository telephoneRepository;
//
//    @Autowired
//    CardBrandRepository cardBrandRepository;
//
//    @Autowired
//    CreditCardRepository creditCardRepository;
//
//    @Autowired
//    CustomerRepository customerRepository;

    @Autowired
    DtoConversion conversion;

    @Autowired
    ItemRequestRepository itemRequestRepository;

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
                Address newAdress = addressDtoToBusiness(dto.getAddress());
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

    private CityDTO cityBusinessToDto(City city){
        CityDTO dto = new CityDTO();

        dto.setId(city.getId());
        dto.setCityName(city.getCityName());

        return dto;
    }

    private StateDTO stateBusinessToDto(State state){
        StateDTO dto = new StateDTO();

        dto.setUf(state.getUf());
        dto.setStateName(state.getStateName());

        return dto;
    }

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

    ///////////////////////////////////

    private Request dtoToBusiness(RequestDTO dto) {
        Request business = new Request();
        DeliveryStatus deliveryStatus = deliveryDtoToBusiness(dto.getDeliveryStatus());
        TypePayment typePayment = typePaymentDtoToBusiness(dto.getTypePayment());
        Address address = addressDtoToBusiness(dto.getAddress());
        Customer customer = conversion.customerDtoToBusiness(dto.getCustomer());
        List<ItemRequest> itemRequests = conversion.itemRequestListFromDto(dto.getItemRequest());

        business.setFreightFixed(dto.getFreightFixed());
        business.setPaymentDate(dto.getPaymentDate());
        business.setPurchaseDate(dto.getPurchaseDate());
        business.setDeliveryStatus(deliveryStatus);
        business.setTypePayment(typePayment);
        business.setAddress(address);
        business.setCustomer(customer);
        business.setItemrequests(itemRequests);


        return business;
    }

    private RequestDTO businessToDto(Request business) {

        RequestDTO dto = new RequestDTO();
        TypePaymentDTO typePaymentDTO = typePaymentBusinessToDto(business.getTypePayment());
        DeliveryStatusDTO deliveryDto = deliveryBusinessToDto(business.getDeliveryStatus());
        AddressDTO addressDTO = addressBusinessToDto(business.getAddress());
        CustomerDTO customerDTO = conversion.customerBusinessToDto(business.getCustomer());
        List <ItemRequestDTO> itemRequestDTO = conversion.itemRequestListToDto(business.getItemrequests());

        dto.setId(business.getId());
        dto.setFreightFixed(business.getFreightFixed());
        dto.setPurchaseDate(business.getPurchaseDate());
        dto.setPaymentDate(business.getPaymentDate());
        dto.setTypePayment(typePaymentDTO);
        dto.setDeliveryStatus(deliveryDto);
        dto.setAddress(addressDTO);
        dto.setCustomer(customerDTO);
        dto.setItemRequest(itemRequestDTO);

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
