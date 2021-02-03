package com.springboot.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String tipo;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String ibge;
    private String pais;

    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "id_pessoa")
    private Pessoa pessoa;

}
