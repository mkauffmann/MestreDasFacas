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
<<<<<<< HEAD
    private CustomerDTO customer;
    private List<ItemRequestDTO> itemRequest;


=======
>>>>>>> 7e99ba98dd0a3f7e7223ec9f34010b6d7c08095f


}
