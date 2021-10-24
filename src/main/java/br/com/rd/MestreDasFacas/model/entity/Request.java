package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity(name = "PEDIDO")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Long id;

    @Column(nullable = false, name = "FRETE_FIXO")
    private Double freightFixed;

    @Column(nullable = false, name = "DATA_COMPRA")
    private Date purchaseDate;

    @Column(nullable = false, name = "DATA_PAGAMENTO")
    private Date paymentDate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_STATUS_PEDIDO")
    private DeliveryStatus deliveryStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_TIPO_PAGAMENTO")
    private TypePayment typePayment;

}
