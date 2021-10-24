package br.com.rd.MestreDasFacas.model.enity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "status_pedido")
public class StatusPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_status_pedido;
    //    @NotNull(message = "Brand field is empty")
    @Column(nullable = false)
    private String description_status_pedido;
    //    @NotNull(message = "Model field is empty")

}

