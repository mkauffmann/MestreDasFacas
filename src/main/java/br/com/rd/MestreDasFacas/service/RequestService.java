package br.com.rd.MestreDasFacas.service;

import br.com.rd.MestreDasFacas.model.dto.*;
import br.com.rd.MestreDasFacas.model.entity.DeliveryStatus;
import br.com.rd.MestreDasFacas.model.entity.TypePayment;
import br.com.rd.MestreDasFacas.model.entity.Request;
import br.com.rd.MestreDasFacas.repository.contract.DeliveryStatusRepository;
import br.com.rd.MestreDasFacas.repository.contract.TypePaymentRepository;
import br.com.rd.MestreDasFacas.repository.contract.RequestRepository;
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

    // Create

    public RequestDTO addRequest(RequestDTO dto) {
        Request newRequest = dtoToBusiness(dto);
        newRequest = requestRepository.save(newRequest);
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
                TypePayment newCable = typePaymentDtoToBusiness(dto.getTypePayment());
                obj.setTypePayment(newCable);
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

    // Request

    private Request dtoToBusiness(RequestDTO dto) {
        Request business = new Request();
        DeliveryStatus deliveryStatus = deliveryDtoToBusiness(dto.getDeliveryStatus());
        TypePayment typePayment = typePaymentDtoToBusiness(dto.getTypePayment());

        business.setFreightFixed(dto.getFreightFixed());
        business.setPaymentDate(dto.getPaymentDate());
        business.setPurchaseDate(dto.getPurchaseDate());
        business.setDeliveryStatus(deliveryStatus);
        business.setTypePayment(typePayment);

        return business;
    }

    private RequestDTO businessToDto(Request business) {

        RequestDTO dto = new RequestDTO();
        TypePaymentDTO typePaymentDTO = typePaymentBusinessToDto(business.getTypePayment());
        DeliveryStatusDTO deliveryDto = deliveryBusinessToDto(business.getDeliveryStatus());

        dto.setId(business.getId());
        dto.setFreightFixed(business.getFreightFixed());
        dto.setPurchaseDate(business.getPurchaseDate());
        dto.setPaymentDate(business.getPaymentDate());
        dto.setTypePayment(typePaymentDTO);
        dto.setDeliveryStatus(deliveryDto);

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
