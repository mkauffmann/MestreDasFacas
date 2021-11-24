package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
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
    private LocalDate purchaseDate;

    @Column(nullable = true, name = "DATA_PAGAMENTO")
    private LocalDate paymentDate;

    // Valor total = soma de todos items:

    @Column(nullable = false, name = "VALOR_TOTAL")
    private Double totalValue;

    // Valor final = valor total + frete

    @Column(nullable = false, name = "VALOR_FINAL")
    private Double finalValue;

    // Inserção de 23/11 - Parcelamento:

    @Column(nullable = false, name = "QUANTIDADE_PARCELAS")
    private Integer installments;

    // Inserção de 24/11 - Valor Parcela = valor total / parcelas:

    @Column(nullable = false, name = "VALOR_PARCELAS")
    private Double installmentsValue;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_STATUS_PEDIDO")
    private DeliveryStatus deliveryStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_TIPO_PAGAMENTO")
    private TypePayment typePayment;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CARTAO_CREDITO")
    private CreditCard creditCard;

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
