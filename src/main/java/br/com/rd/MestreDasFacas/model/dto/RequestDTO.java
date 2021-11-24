package br.com.rd.MestreDasFacas.model.dto;

import br.com.rd.MestreDasFacas.model.entity.CreditCard;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
public class RequestDTO {

    private Long id;
    private Double freightFixed;
    private LocalDate purchaseDate;
    private LocalDate paymentDate;
    private Double totalValue;
    private Double finalValue;
    private TypePaymentDTO typePayment;
    private CreditCardDTO creditCard;
    private DeliveryStatusDTO deliveryStatus;
    private AddressDTO address;
    private CustomerDTO customer;
    private List<ItemRequestDTO> itemRequest;
    private Integer installments;
    private Double installmentsValue;

}
