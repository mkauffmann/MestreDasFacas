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

    public RequestDTO addRequest(RequestDTO dto) throws Exception {
        Request newRequest = dtoToBusiness(dto);

        sendNewRequestEmail(newRequest);

        if(validateInventory(newRequest.getItemrequests())){
            for (ItemRequest i : newRequest.getItemrequests()) {
                Product product = i.getProduct();
                Optional<Inventory> op = inventoryRepository.myFindById(product.getId());

                if (op.isPresent()) {
                    Inventory obj = op.get();
                    Integer qtdEstoque = obj.getQuantityInventory();
                    Integer qtdCompra = Math.toIntExact(i.getQuantity());

                    obj.setQuantityInventory(qtdEstoque - qtdCompra);
                    inventoryRepository.save(obj);
                }


            }
            newRequest = requestRepository.save(newRequest);

            return businessToDto(newRequest);
        }
        return null;

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

            }
            if (dto.getAddress() != null) {
                Address newAdress = conversion.addressDtoToBusiness(dto.getAddress());
                obj.setAddress(newAdress);
            }
            if (dto.getItemRequest() != null) {
                List<ItemRequest> itemRequest = conversion.itemRequestListFromDto(dto.getItemRequest());
                List<ItemRequest> listUpdate = obj.getItemrequests();

                for (ItemRequest i : itemRequest) {
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
        List<Request> requestList = this.requestRepository.findByCustomerIdEquals(id);
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


    private Double calculateTotalValue(List<ItemRequest> items) {
        Double totalValue = 0.0;
        for (ItemRequest item : items) {
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
        if (dto.getPaymentDate() != null) {
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
        business.setFinalValue(business.getFreightFixed() + valorTotal);
        Double valorTotalParcelado = business.getFinalValue() / dto.getInstallments();


        business.setInstallmentsValue(valorTotalParcelado);
        business.setTotalValue(valorTotal);
        


        if (dto.getCreditCard() != null) {
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
        List<ItemRequestDTO> itemRequestDTO = conversion.itemRequestListToDto(business.getItemrequests());


        dto.setId(business.getId());
        dto.setFreightFixed(business.getFreightFixed());
        dto.setPurchaseDate(business.getPurchaseDate());
        if (business.getPaymentDate() != null) {
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

        if (business.getCreditCard() != null) {
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

    private boolean validateInventory(List<ItemRequest> items) throws Exception {

        for (ItemRequest i : items) {
            Product product = i.getProduct();
            Optional<Inventory> op = inventoryRepository.myFindById(product.getId());

            if (op.isPresent()) {
                Inventory obj = op.get();
                Integer qtdEstoque = obj.getQuantityInventory();
                Integer qtdCompra = Math.toIntExact(i.getQuantity());

                if (qtdCompra > qtdEstoque) {
                    throw new Exception("Quantidade insuficiente do produto " + product.getProductName() + " em estoque");
                }

            }
        }
        return true;
    }


    // METODO PARA ENVIAR EMAIL DE BOAS VINDAS PARA O CLIENTE.
    public void sendNewRequestEmail(Request newRequest){

        EmailModel email = new EmailModel();
        email.setSendDateEmail(LocalDateTime.now());
        email.setOwnerRef(newRequest.getId());
        email.setEmailTo(newRequest.getCustomer().getEmail());
        email.setEmailFrom("mestredasfacas2021@gmail.com");
        email.setSubject("Recebemos seu pedido ");
        email.setText(String.format("Olá, %s, Recebemos o seu pedido :) \n "
                +"Não se preocupe atualmente estamos confirmando seu pagamento. "
                +"Em breve entraremos em contato com o novo Status do seu pedido! ", newRequest.getCustomer().getName()));

        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(email.getEmailFrom());
            message.setTo(email.getEmailTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            emailSender.send(message);
            email.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e ) {
            email.setStatusEmail(StatusEmail.ERROR);

        }finally {
            emailRepository.save(email);
        }

    }}
