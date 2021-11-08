package br.com.rd.MestreDasFacas.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "ENDERECO")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Long id;

    @Column(name = "LOGRADOURO", nullable = false)
    private String street;

    @Column(name = "NUMERO", nullable = false)
    private Integer number;

    @Column(name = "COMPLEMENTO")
    private String complement;

    @Column(name = "CEP", nullable = false)
    private String cep;

    @Column(name = "BAIRRO", nullable = false)
    private String neighborhood;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_CIDADE")
    private City city;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ID_UF")
    private State state;
}
