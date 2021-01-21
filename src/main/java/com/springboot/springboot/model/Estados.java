package com.springboot.springboot.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "estados")
public class Estados {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String sigla;
    private String nome;
}
