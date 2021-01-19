package com.springboot.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String numero;
    private String tipo;

    @ManyToOne
    @org.hibernate.annotations.ForeignKey(name = "id_pessoa")
    private Pessoa pessoa;
}
