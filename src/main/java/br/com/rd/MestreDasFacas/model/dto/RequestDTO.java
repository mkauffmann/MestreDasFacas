package br.com.rd.MestreDasFacas.model.dto;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class RequestDTO {

    private Long id;
    private Double freightFixed;
    private Date purchaseDate;
    private Date paymentDate;
    private TypePaymentDTO typePayment;
    private DeliveryStatusDTO deliveryStatus;
    private AddressDTO address;
    private CustomerDTO customer;
    private List<ItemRequestDTO> itemRequest;


}