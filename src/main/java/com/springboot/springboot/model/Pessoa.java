package com.springboot.springboot.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Nome não pode ser nulo!")
    @NotEmpty(message = "Nome não pode ser vázio!")
    private String nome;

    @NotNull(message = "Sobrenome não pode ser nulo!")
    @NotEmpty(message = "Sobrenome não pode ser vázio!")
    private String sobrenome;

    @NotNull(message = "Email não pode ser nulo!")
    @NotEmpty(message = "Email não pode ser vázio!")
    private String email;

    @Min(value = 10, message = "Idade mínima 10 anos!")
    @Max(value = 99, message = "Idade maxíma 99 anos!")
    private int idade;

    @OneToMany(mappedBy = "pessoa", /*orphanRemoval = true,*/ cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    @OneToMany(mappedBy = "pessoa",  cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date createAt;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date updateAt;

    @Lob
    private byte[] foto;

    private String nameFileFoto;
    private String typeFileFoto;


}
