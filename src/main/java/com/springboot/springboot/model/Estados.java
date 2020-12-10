package com.springboot.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "estados")
public class Estados {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String sigla;
    private String nome;
}
