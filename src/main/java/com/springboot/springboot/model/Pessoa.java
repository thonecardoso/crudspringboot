package com.springboot.springboot.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String endereco;
    private String complemento;
    private String pais;
    private String estado;
    private String cep;

    @OneToMany(mappedBy = "pessoa")
    private List<Telefone> telefones;
}
