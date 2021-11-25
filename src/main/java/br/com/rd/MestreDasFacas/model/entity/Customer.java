package br.com.rd.MestreDasFacas.model.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "CLIENTE")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NOME_CLIENTE", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate birthDate;

    @Column(name = "SENHA", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "ID_GENERO")
    private Gender gender;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "CLIENTE_TELEFONE",
            joinColumns = {@JoinColumn(name = "ID_CLIENTE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_TELEFONE")}
    )
    private List<Telephone> telephones;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "CLIENTE_ENDERECO",
            joinColumns = {@JoinColumn(name = "ID_CLIENTE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_ENDERECO")}
    )
    private List<Address> addresses;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "CLIENTE_CARTAO_CREDITO",
            joinColumns = {@JoinColumn(name = "ID_CLIENTE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_CARTAO_CREDITO")}
    )
    private List<CreditCard> creditCards;


    @Column ( name = "reset_password_token")
    private String resetPasswordToken;




}
