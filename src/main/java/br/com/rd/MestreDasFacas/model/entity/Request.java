package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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

    @Column(nullable = false, name = "VALOR_TOTAL")
    private Double totalValue;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_STATUS_PEDIDO")
    private DeliveryStatus deliveryStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_TIPO_PAGAMENTO")
    private TypePayment typePayment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_ENDERECO")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CLIENTE")
    private Customer customer;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "ITEM_PEDIDO_PEDIDO",
            joinColumns = {@JoinColumn(name = "ID_PEDIDO")},
            inverseJoinColumns = {@JoinColumn(name = "ID_ITEM_PEDIDO")})
    private List<ItemRequest> itemrequests;






}
